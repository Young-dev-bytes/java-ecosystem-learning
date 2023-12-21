/*
 * Copyright (c) 2002, 2006, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.utilities;

import java.util.*;
import sun.jvm.hotspot.oops.*;

/** ReversePtrs hashtable. */

public class ReversePtrs  {
  private HashMap rp;

  public ReversePtrs() {
    rp = new HashMap();
  }

  public void put(LivenessPathElement from, Oop to) {
    if (to == null) return;
    ArrayList al = (ArrayList) rp.get((Object) to);
    if (al == null) al = new ArrayList();
    // Inserting at the beginning is a hack to change the reported
    // paths from LivenessAnalysis to look more like they used to;
    // otherwise paths through the Finalizer queue to popular objects
    // seem to be preferred
    al.add(0, (Object) from);
    rp.put((Object) to, (Object) al);
  }

  /** Returns an ArrayList of the incoming references to this Oop if
      it is alive, and null if it is dead according to the
      ReversePtrsAnalysis. Currently not all roots are scanned so this
      result is frequently inaccurate for JVM-internal objects, but is
      usually correct for Java-level objects. */
  public ArrayList/*<LivenessPathElement>*/ get(Oop obj) {
    return (ArrayList) rp.get((Object)obj);
  }
}
