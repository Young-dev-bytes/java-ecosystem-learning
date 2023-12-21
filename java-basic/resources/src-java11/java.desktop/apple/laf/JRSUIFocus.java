/*
 * Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved.
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

package apple.laf;

public class JRSUIFocus {
    private static final int SUCCESS = 0;
    private static final int NULL_PTR = -1;
    private static final int NULL_CG_REF = -2;

    // from HITheme.h
    public static final int RING_ONLY = 0;
    public static final int RING_ABOVE = 1;
    public static final int RING_BELOW = 2;

    private static native int beginNativeFocus(final long cgContext, final int ringStyle);
    private static native int endNativeFocus(final long cgContext);

    final long cgContext;
    public JRSUIFocus(final long cgContext) {
        this.cgContext = cgContext;
    }

    public void beginFocus(final int ringStyle) {
        testForFailure(beginNativeFocus(cgContext, ringStyle));
    }

    public void endFocus() {
        testForFailure(endNativeFocus(cgContext));
    }

    static void testForFailure(final int status) {
        if (status == SUCCESS) return;

        switch(status) {
            case NULL_PTR: throw new RuntimeException("Null pointer exception in native JRSUI");
            case NULL_CG_REF: throw new RuntimeException("Null CG reference in native JRSUI");
            default: throw new RuntimeException("JRSUI draw focus problem: " + status);
        }
    }
}
