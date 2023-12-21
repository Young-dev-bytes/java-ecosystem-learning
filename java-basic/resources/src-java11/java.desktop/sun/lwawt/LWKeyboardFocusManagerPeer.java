/*
 * Copyright (c) 2011, 2013, Oracle and/or its affiliates. All rights reserved.
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

package sun.lwawt;

import java.awt.Component;
import java.awt.Window;
import sun.awt.KeyboardFocusManagerPeerImpl;

public class LWKeyboardFocusManagerPeer extends KeyboardFocusManagerPeerImpl {
    private static final LWKeyboardFocusManagerPeer inst = new LWKeyboardFocusManagerPeer();

    private Window focusedWindow;
    private Component focusOwner;

    public static LWKeyboardFocusManagerPeer getInstance() {
        return inst;
    }

    private LWKeyboardFocusManagerPeer() {
    }

    @Override
    public void setCurrentFocusedWindow(Window win) {
        LWWindowPeer from, to;

        synchronized (this) {
            if (focusedWindow == win) {
                return;
            }

            from = (LWWindowPeer)LWToolkit.targetToPeer(focusedWindow);
            to = (LWWindowPeer)LWToolkit.targetToPeer(win);

            focusedWindow = win;
        }

        if (from != null) {
            from.updateSecurityWarningVisibility();
        }

        if (to != null) {
            to.updateSecurityWarningVisibility();
        }
    }

    @Override
    public Window getCurrentFocusedWindow() {
        synchronized (this) {
            return focusedWindow;
        }
    }

    @Override
    public Component getCurrentFocusOwner() {
        synchronized (this) {
            return focusOwner;
        }
    }

    @Override
    public void setCurrentFocusOwner(Component comp) {
        synchronized (this) {
            focusOwner = comp;
        }
    }
}
