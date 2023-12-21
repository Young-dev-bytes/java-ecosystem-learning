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

import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.peer.CheckboxMenuItemPeer;

import sun.awt.SunToolkit;

public class CCheckboxMenuItem extends CMenuItem implements CheckboxMenuItemPeer {
    volatile boolean fAutoToggle = true;
    volatile boolean fIsIndeterminate = false;

    private native void nativeSetState(long modelPtr, boolean state);
    private native void nativeSetIsCheckbox(long modelPtr);

    CCheckboxMenuItem(final CheckboxMenuItem target) {
        super(target);
        execute(this::nativeSetIsCheckbox);
        setState(target.getState());
    }

    // MenuItemPeer implementation
    @Override
    public void setState(final boolean state) {
        execute(ptr -> nativeSetState(ptr, state));
    }

    public void handleAction(final boolean state) {
        final CheckboxMenuItem target = (CheckboxMenuItem)getTarget();
        SunToolkit.executeOnEventHandlerThread(target, new Runnable() {
            public void run() {
                target.setState(state);
            }
        });
        ItemEvent event = new ItemEvent(target, ItemEvent.ITEM_STATE_CHANGED, target.getLabel(), state ? ItemEvent.SELECTED : ItemEvent.DESELECTED);
        SunToolkit.postEvent(SunToolkit.targetToAppContext(getTarget()), event);
    }

    public void setIsIndeterminate(final boolean indeterminate) {
        fIsIndeterminate = indeterminate;
    }

    private boolean isAutoToggle() {
        return fAutoToggle;
    }

    public void setAutoToggle(boolean b) {
        fAutoToggle = b;
    }
}
