/*
 * Copyright (c) 2011, 2017, Oracle and/or its affiliates. All rights reserved.
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

package com.apple.eawt.event;

/**
 * Listener interface for receiving notification that a continuous gesture
 * (like rotate or scale) has started or stopped.
 *
 * @see MagnificationListener
 * @see RotationListener
 * @see GesturePhaseEvent
 * @see GestureUtilities
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public interface GesturePhaseListener extends GestureListener {
    /**
     * Invoked when the user has started a continuous gesture.
     * @param e representing the start of a continuous gesture.
     */
    public void gestureBegan(final GesturePhaseEvent e);

    /**
     * Invoked when the user has stopped a continuous gesture.
     * @param e representing the end of a continuous gesture.
     */
    public void gestureEnded(final GesturePhaseEvent e);
}
