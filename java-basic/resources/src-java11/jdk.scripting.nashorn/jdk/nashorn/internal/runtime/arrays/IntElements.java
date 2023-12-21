/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates. All rights reserved.
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
 *
 */
package jdk.nashorn.internal.runtime.arrays;

/**
 * Marker interface for any ContinuousArray with int elements
 * Used for type checks that throw ClassCastExceptions and force relinks
 * for fast NativeArray specializations of builtin methods
 */
public interface IntElements extends IntOrLongElements {
    //empty
}
