/*
 * Copyright (c) 2001, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.jvm.hotspot.interpreter;

import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.utilities.*;

public class OopMapCacheEntry {
  // Iteration
  public boolean isValue(int offset) { return !entryAt(offset); }
  public boolean isOop  (int offset) { return entryAt(offset);  }
  public void    iterateOop(OffsetClosure oopClosure) {
    int n = numberOfEntries();
    for (int i = 0; i < n; i++) {
      if (entryAt(i)) {
        oopClosure.offsetDo(i);
      }
    }
  }

  // Initialization
  public void fill(Method method, int bci) {
    this.method = method;
    this.bci = bci;
    if (method.isNative()) {
      // Native method activations have oops only among the parameters and one
      // extra oop following the parameters (the mirror for static native methods).
      fillForNative();
    } else {
      OopMapForCacheEntry gen = new OopMapForCacheEntry(method, bci, this);
      gen.computeMap();
    }
  }

  public void setMask(CellTypeStateList vars,
                      CellTypeStateList stack,
                      int stackTop) {
    // compute bit mask size
    int maxLocals = (int) method.getMaxLocals();
    int nEntries  = maxLocals + stackTop;
    maskSize      = nEntries;
    allocateBitMask();

    CellTypeStateList curList = vars;
    int listIdx = 0;

    for (int entryIdx = 0; entryIdx < nEntries; entryIdx++, listIdx++) {
      // switch to stack when done with locals
      if (entryIdx == maxLocals) {
        curList = stack;
        listIdx = 0;
      }

      CellTypeState cell = curList.get(listIdx);
      // set oop bit
      if ( cell.isReference()) {
        mask.atPut(entryIdx, true);
      }
    }

    // verify bit mask
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(verifyMask(vars, stack, maxLocals, stackTop), "mask could not be verified");
    }
  }

  //----------------------------------------------------------------------
  // Internals only below this point
  //
  private Method method;    // the method for which the mask is valid
  private int    bci;       // the bci    for which the mask is valid
  private int    maskSize;  // the required mask size in bits
  private BitMap mask;      // may be null if mask is empty

  Method method()        { return method; }
  int bci()              { return bci; }
  int numberOfEntries()  { return maskSize; }
  boolean entryAt(int offset) {
    return mask.at(offset);
  }

  void setEmptyMask()    { mask = null; }
  void allocateBitMask() {
    if (maskSize > 0) {
      mask = new BitMap(maskSize);
    }
  }

  // fills the bit mask for native calls
  void fillForNative() {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(method.isNative(), "method must be native method");
    }
    maskSize = (int) method.getSizeOfParameters();
    allocateBitMask();
    // fill mask for parameters
    MaskFillerForNative mf = new MaskFillerForNative(method, mask, maskSize);
    mf.generate();
  }

  static class VerifyClosure implements OffsetClosure {
    private OopMapCacheEntry entry;
    private boolean          failed;

    VerifyClosure(OopMapCacheEntry entry)          { this.entry = entry; }
    public void offsetDo(int offset)               { if (!entry.isOop(offset)) failed = true; }
    boolean failed()                               { return failed; }
  }

  boolean verifyMask(CellTypeStateList vars, CellTypeStateList stack, int maxLocals, int stackTop) {
    // Check mask includes map
    VerifyClosure blk = new VerifyClosure(this);
    iterateOop(blk);
    if (blk.failed()) return false;

    // Check if map is generated correctly
    for(int i = 0; i < maxLocals; i++) {
      boolean v1 = isOop(i);
      boolean v2 = vars.get(i).isReference();
      if (Assert.ASSERTS_ENABLED) {
        Assert.that(v1 == v2, "locals oop mask generation error");
      }
    }

    for(int j = 0; j < stackTop; j++) {
      boolean v1 = isOop(maxLocals + j);
      boolean v2 = stack.get(j).isReference();
      if (Assert.ASSERTS_ENABLED) {
        Assert.that(v1 == v2, "stack oop mask generation error");
      }
    }
    return true;
  }
}
