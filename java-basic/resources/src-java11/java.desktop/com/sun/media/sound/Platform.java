/*
 * Copyright (c) 1999, 2018, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.media.sound;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.StringTokenizer;

/**
 * Audio configuration class for exposing attributes specific to the platform or system.
 *
 * @author Kara Kytle
 * @author Florian Bomers
 */
final class Platform {

    // native library we need to load
    private static final String libName = "jsound";

    private static boolean isNativeLibLoaded;

    // SYSTEM CHARACTERISTICS
    // vary according to hardware architecture

    // intel is little-endian.  sparc is big-endian.
    private static boolean bigEndian;

    static {
        if(Printer.trace)Printer.trace(">> Platform.java: static");

        loadLibraries();
    }

    /**
     * Private constructor.
     */
    private Platform() {
    }

    /**
     * Dummy method for forcing initialization.
     */
    static void initialize() {
        if(Printer.trace)Printer.trace("Platform: initialize()");
    }

    /**
     * Determine whether the system is big-endian.
     */
    static boolean isBigEndian() {
        return bigEndian;
    }

    /**
     * Load the native library or libraries.
     */
    private static void loadLibraries() {
        if(Printer.trace)Printer.trace(">>Platform.loadLibraries");

        // load the native library
        isNativeLibLoaded = true;
        try {
            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                System.loadLibrary(libName);
                return null;
            });
        } catch (Throwable t) {
            if (Printer.err) Printer.err("Couldn't load library "+libName+": "+t.toString());
            isNativeLibLoaded = false;
        }
        if (isNativeLibLoaded) {
            bigEndian = nIsBigEndian();
        }
    }

    static boolean isMidiIOEnabled() {
        if (Printer.debug) Printer.debug("Platform: Checking for MidiIO; library is loaded=" + isNativeLibLoaded);
        return isNativeLibLoaded;
    }

    static boolean isPortsEnabled() {
        if (Printer.debug) Printer.debug("Platform: Checking for Ports; library is loaded=" + isNativeLibLoaded);
        return isNativeLibLoaded;
    }

    static boolean isDirectAudioEnabled() {
        if (Printer.debug) Printer.debug("Platform: Checking for DirectAudio; library is loaded=" + isNativeLibLoaded);
        return isNativeLibLoaded;
    }

    // the following native method is implemented in Platform.c
    private static native boolean nIsBigEndian();
}
