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
 * Listener interface for receiving magnification events.
 *
 * @see MagnificationEvent
 * @see GesturePhaseListener
 * @see GestureUtilities
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public interface MagnificationListener extends GestureListener {
    /**
     * Invoked when a magnification gesture is performed by the user.
     * @param e containing the scale of the magnification.
     */
    public void magnify(final MagnificationEvent e);
}
