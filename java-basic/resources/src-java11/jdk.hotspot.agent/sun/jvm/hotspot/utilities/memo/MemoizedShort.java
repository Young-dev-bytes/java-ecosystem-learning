/*
 * Copyright (c) 2000, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.utilities.memo;

/** A memoized short. Override {@link #computeValue} in subclasses;
    call {@link #getValue} in using code. */

public abstract class MemoizedShort {
  private boolean computed;
  private short value;

  /** Should compute the value of this memoized object. This will only
      be called once, upon the first call to {@link #getValue}. */
  protected abstract short computeValue();

  /** Public accessor for the memoized value. */
  public short getValue() {
    if (!computed) {
      value = computeValue();
      computed = true;
    }
    return value;
  }
}
