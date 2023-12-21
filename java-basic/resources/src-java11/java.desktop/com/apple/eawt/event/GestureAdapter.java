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
 * Abstract adapter class for receiving gesture events. This class is provided
 * as a convenience for creating listeners.
 *
 * Subclasses registered with {@link GestureUtilities#addGestureListenerTo}
 * will receive all phase, magnification, rotation, and swipe events.
 *
 * @see GestureUtilities
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public abstract class GestureAdapter implements GesturePhaseListener, MagnificationListener, RotationListener, SwipeListener {
    public void gestureBegan(final GesturePhaseEvent e) { }
    public void gestureEnded(final GesturePhaseEvent e) { }
    public void magnify(final MagnificationEvent e) { }
    public void rotate(final RotationEvent e) { }
    public void swipedDown(final SwipeEvent e) { }
    public void swipedLeft(final SwipeEvent e) { }
    public void swipedRight(final SwipeEvent e) { }
    public void swipedUp(final SwipeEvent e) { }
}
