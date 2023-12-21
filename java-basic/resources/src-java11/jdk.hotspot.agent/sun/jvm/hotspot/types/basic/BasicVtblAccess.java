/*
 * Copyright (c) 2002, 2005, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.types.basic;

import java.util.*;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.types.*;

public abstract class BasicVtblAccess implements VtblAccess {
  protected SymbolLookup symbolLookup;
  protected String[] dllNames;

  private Map typeToVtblMap = new HashMap();

  public BasicVtblAccess(SymbolLookup symbolLookup,
                         String[] dllNames) {
    this.symbolLookup = symbolLookup;
    this.dllNames = dllNames;
  }

  static Object nullAddress = new Object();

  public Address getVtblForType(Type type) {
    if (type == null) {
      return null;
    }
    Object result = typeToVtblMap.get(type);
    if (result == nullAddress) {
        return null;
    }
    if (result != null) {
      return (Address)result;
    }
    String vtblSymbol = vtblSymbolForType(type);
    if (vtblSymbol == null) {
      typeToVtblMap.put(type, nullAddress);
      return null;
    }
    for (int i = 0; i < dllNames.length; i++) {
      Address addr = symbolLookup.lookup(dllNames[i], vtblSymbol);
      if (addr != null) {
        typeToVtblMap.put(type, addr);
        return addr;
      }
    }
    typeToVtblMap.put(type, nullAddress);
    return null;
  }

  public void clearCaches() {
    typeToVtblMap.clear();
  }

  protected abstract String vtblSymbolForType(Type type);
}
