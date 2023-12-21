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

package com.apple.eawt.event;

/**
 * Event encapsulating a relative rotation performed by the user.
 *
 * @see RotationListener
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public class RotationEvent extends GestureEvent {
    final double rotation;

    RotationEvent(final double rotation) {
        // package private
        this.rotation = rotation;
    }

    /**
     * @return an abstract measure of rotation (clockwise is negative)
     */
    public double getRotation() {
        return rotation;
    }
}
