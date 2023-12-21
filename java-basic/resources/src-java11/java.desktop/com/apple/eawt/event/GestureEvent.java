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

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Abstract event all gestures inherit from.
 *
 * Note: GestureEvent is not subclass of {@link AWTEvent} and is not dispatched
 * directly from the {@link EventQueue}. This is an intentional design decision
 * to prevent collision with an official java.awt.* gesture event types subclassing
 * {@link InputEvent}.
 *
 * {@link GestureListener}s are only notified from the AWT Event Dispatch thread.
 *
 * @see GestureUtilities
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public abstract class GestureEvent {
    boolean consumed;

    GestureEvent() {
        // package private
    }

    /**
     * Consuming an event prevents listeners later in the chain or higher in the
     * component hierarchy from receiving the event.
     */
    public void consume() {
        consumed = true;
    }

    /**
     * @return if the event has been consumed
     */
    protected boolean isConsumed() {
        return consumed;
    }
}
