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

package sun.jvm.hotspot.types;

import java.util.*;

/** <P> This subinterface of Type provides accessors to deal with all
    C integer types. The observation is that, according to the C
    specification, there is no guarantee that the integer types of
    char, short, int, and long will not all be of the same size,
    meaning that it is incorrect to use any Java primitive type that
    is too small to hold all of these values when talking about C
    integer types, signed or unsigned. </P>

    <P> Therefore we use long, the largest Java primitive type,
    universally to hold C integer field values (deciding that a
    ubiquitous change to BigInteger is not currently advantageous).
    Routines which read C integers from fields know the fields' sizes
    and signedness and read the appropriate number of bytes and handle
    sign- or zero- extension for signed and unsigned types,
    respectively. Unfortunately, since long is a signed 64-bit
    integer, there will be problems handling C's unsigned 64-bit
    integers, but these problems must be dealt with by the user. </P> */
public interface CIntegerType extends Type {
  /** Is this integer type unsigned? */
  public boolean isUnsigned();

  /** What is the maximum value of this type? Note that this will not
      work properly for unsigned long longs. */
  public long maxValue();

  /** What is the minimum value of this type? Note that this will not
      work properly for unsigned long longs. */
  public long minValue();
}
