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

package com.apple.eawt;

class _AppMiscHandlers {
    private static boolean isSuddenTerminationEnabled;

    private static native void nativeOpenHelpViewer();

    private static native void nativeRequestActivation(final boolean allWindows);
    private static native void nativeRequestUserAttention(final boolean critical);

    private static native void nativeEnableSuddenTermination();
    private static native void nativeDisableSuddenTermination();

    static void openHelpViewer() {
        nativeOpenHelpViewer();
    }

    static void requestActivation(final boolean allWindows) {
        nativeRequestActivation(allWindows);
    }

    static void requestUserAttention(final boolean critical) {
        nativeRequestUserAttention(critical);
    }

    static void enableSuddenTermination() {
        isSuddenTerminationEnabled = true;
        nativeEnableSuddenTermination();
    }

    static void disableSuddenTermination() {
        isSuddenTerminationEnabled = false;
        nativeDisableSuddenTermination();
    }

    public static boolean isSuddenTerminationEnbaled() {
        return isSuddenTerminationEnabled;
    }
}
