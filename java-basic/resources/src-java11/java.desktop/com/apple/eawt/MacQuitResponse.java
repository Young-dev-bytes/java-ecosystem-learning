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

import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;
import java.awt.desktop.QuitStrategy;

/**
 * Used to respond to a request to quit the application.
 * The QuitResponse may be used after the {@link QuitHandler#handleQuitRequestWith(QuitEvent, QuitResponse)} method has returned, and may be used from any thread.
 *
 * @see Application#setQuitHandler(QuitHandler)
 * @see QuitHandler
 * @see Application#setQuitStrategy(QuitStrategy)
 *
 * @since Java for Mac OS X 10.6 Update 3
 * @since Java for Mac OS X 10.5 Update 8
 */
public class MacQuitResponse implements QuitResponse {
    final _AppEventHandler appEventHandler;

    MacQuitResponse(final _AppEventHandler appEventHandler) {
        this.appEventHandler = appEventHandler;
    }

    /**
     * Notifies the external quit requester that the quit will proceed, and performs the default {@link QuitStrategy}.
     */
    @Override
    public void performQuit() {
        if (appEventHandler.currentQuitResponse != this) return;
        appEventHandler.performQuit();
    }

    /**
     * Notifies the external quit requester that the user has explicitly canceled the pending quit, and leaves the application running.
     * <b>Note: this will cancel a pending log-out, restart, or shutdown.</b>
     */
    @Override
    public void cancelQuit() {
        if (appEventHandler.currentQuitResponse != this) return;
        appEventHandler.cancelQuit();
    }
}
