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

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.peer.MenuBarPeer;

import sun.awt.AWTAccessor;

public final class CMenuBar extends CMenuComponent implements MenuBarPeer {

    private int nextInsertionIndex = -1;

    public CMenuBar(MenuBar target) {
        super(target);
    }

    @Override
    long createModel() {
        return nativeCreateMenuBar();
    }

    @Override
    public void addHelpMenu(final Menu m) {
        final CMenu cMenu = AWTAccessor.getMenuComponentAccessor().getPeer(m);
        execute(parentPtr -> cMenu.execute(
                menuPtr -> nativeSetHelpMenu(parentPtr, menuPtr)));
    }

    public int getNextInsertionIndex() {
        return nextInsertionIndex;
    }

    // Used by ScreenMenuBar to add newly visible menus in the right spot.
    public void setNextInsertionIndex(int index) {
        nextInsertionIndex = index;
    }

    @Override
    public void addMenu(Menu m) {
        // Nothing to do here -- we added it when the menu was created.
    }

    @Override
    public void delMenu(final int index) {
        execute(ptr -> nativeDelMenu(ptr, index));
    }

    private native long nativeCreateMenuBar();
    private native void nativeSetHelpMenu(long menuBarPtr, long menuPtr);
    private native void nativeDelMenu(long menuBarPtr, int index);
}
