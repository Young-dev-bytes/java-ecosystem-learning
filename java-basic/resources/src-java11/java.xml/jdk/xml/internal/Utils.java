/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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

package jdk.xml.internal;

import java.util.Arrays;

/**
 * General utility. Use JdkXmlUtils for XML processing related functions.
 */
public class Utils {
    /**
     * Creates a new array with copies of the original array and additional items
     * appended to the end of it.
     *
     * @param original the original array
     * @param items items to be appended to the original array
     * @return a new array with copies of the original array and additional items
     */
    public static Class<?>[] arraysAppend(final Class<?>[] original, final Class<?>... items) {
        if (original == null && items == null) {
            return null;
        }
        if (items == null) {
            return Arrays.copyOf(original, original.length);
        }
        if (original == null) {
            return Arrays.copyOf(items, items.length);
        }

        Class<?>[] result = Arrays.copyOf(original, original.length + items.length);
        System.arraycopy(items, 0, result, original.length, items.length);
        return result;
    }
}
