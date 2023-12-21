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

package com.apple.eawt;

import java.awt.Window;

import com.apple.eawt.event.FullScreenEvent;

/**
 * Abstract adapter class for receiving fullscreen events. This class is provided
 * as a convenience for creating listeners.
 *
 * Subclasses registered with {@link FullScreenUtilities#addFullScreenListenerTo(Window, FullScreenListener)}
 * will receive all entering/entered/exiting/exited full screen events.
 *
 * @see FullScreenUtilities
 *
 * @since Java for Mac OS X 10.7 Update 1
 */
public abstract class FullScreenAdapter implements FullScreenListener {
        public void windowEnteringFullScreen(final FullScreenEvent e) {}
        public void windowEnteredFullScreen(final FullScreenEvent e) {}
        public void windowExitingFullScreen(final FullScreenEvent e) {}
        public void windowExitedFullScreen(final FullScreenEvent e) {}
}
