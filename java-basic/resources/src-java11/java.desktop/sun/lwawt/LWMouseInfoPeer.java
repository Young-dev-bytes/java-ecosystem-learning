/*
 * Copyright (c) 2011, 2015, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Point;
import java.awt.Window;

import java.awt.peer.MouseInfoPeer;

import sun.awt.AWTAccessor;

public class LWMouseInfoPeer implements MouseInfoPeer {

    @Override
    public int fillPointWithCoords(Point point) {
        LWCursorManager cursorManager =
            LWToolkit.getLWToolkit().getCursorManager();
        Point cursorPos = cursorManager.getCursorPosition();
        point.x = cursorPos.x;
        point.y = cursorPos.y;
        // TODO: multiscreen
        return 0;
    }

    @Override
    public boolean isWindowUnderMouse(Window w) {
        if (w == null) {
            return false;
        }

        LWWindowPeer windowPeer = AWTAccessor.getComponentAccessor().getPeer(w);
        if (windowPeer == null) {
            return false;
        }

        return LWToolkit.getLWToolkit().getPlatformWindowUnderMouse() == windowPeer.getPlatformWindow();
    }

}

