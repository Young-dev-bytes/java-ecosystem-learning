/*
 * Copyright (c) 2000, 2021, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.compiler;

import sun.jvm.hotspot.code.*;

public class OopMapStream {
  private CompressedReadStream stream;
  private ImmutableOopMap oopMap;
  private int size;
  private int position;
  private OopMapValue omv;
  private boolean omvValid;

  public OopMapStream(ImmutableOopMap oopMap) {
    stream = new CompressedReadStream(oopMap.getData());
    size = (int) oopMap.getCount();
    position = 0;
    omv = new OopMapValue();
    omvValid = false;
  }

  public boolean isDone() {
    if (!omvValid) {
      findNext();
    }
    return !omvValid;
  }

  public void next() {
    findNext();
  }

  public OopMapValue getCurrent() {
    return omv;
  }

  //--------------------------------------------------------------------------------
  // Internals only below this point
  //

  private void findNext() {
    if (position++ < size) {
      omv.readFrom(stream);
      omvValid = true;
      return;
    }
    omvValid = false;
  }
}
