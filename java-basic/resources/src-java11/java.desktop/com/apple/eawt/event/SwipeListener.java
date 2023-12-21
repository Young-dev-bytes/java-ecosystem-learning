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
 * Listener interface for receiving swipe events. A single swipe event
 * may be both vertical and horizontal simultaneously, invoking both
 * a vertical and horizontal method.
 *
 * @see SwipeEvent
 * @see GestureUtilities
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public interface SwipeListener extends GestureListener {
    /**
     * Invoked when an upwards swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    public void swipedUp(final SwipeEvent e);

    /**
     * Invoked when a downward swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    public void swipedDown(final SwipeEvent e);

    /**
     * Invoked when a leftward swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    public void swipedLeft(final SwipeEvent e);

    /**
     * Invoked when a rightward swipe gesture is performed by the user.
     * @param e representing the occurrence of a swipe.
     */
    public void swipedRight(final SwipeEvent e);
}
