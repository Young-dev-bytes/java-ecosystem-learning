/*
 * Copyright (c) 2011, 2016, Oracle and/or its affiliates. All rights reserved.
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

package sun.lwawt.macosx;

import java.awt.Component;
import java.awt.Event;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.peer.PopupMenuPeer;

final class CPopupMenu extends CMenu implements PopupMenuPeer {

    CPopupMenu(PopupMenu target) {
        super(target);
    }

    @Override
    long createModel() {
        return nativeCreatePopupMenu();
    }

    private native long nativeCreatePopupMenu();
    private native long nativeShowPopupMenu(long modelPtr, int x, int y);

    @Override
    @SuppressWarnings("deprecation")
    public void show(Event e) {
        Component origin = (Component)e.target;
        if (origin != null) {
            Point loc = origin.getLocationOnScreen();
            e.x += loc.x;
            e.y += loc.y;
            execute(ptr -> nativeShowPopupMenu(ptr, e.x, e.y));
        }
    }
}
