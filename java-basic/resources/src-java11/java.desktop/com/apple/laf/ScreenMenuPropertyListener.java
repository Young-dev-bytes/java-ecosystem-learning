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

package com.apple.laf;

import java.beans.*;

import javax.accessibility.*;
import javax.swing.*;

class ScreenMenuPropertyListener implements PropertyChangeListener {
    ScreenMenuPropertyHandler fMenu;

    ScreenMenuPropertyListener(final ScreenMenuPropertyHandler mc) {
        fMenu = mc;
    }

    /**
     * This method gets called when a bound property is changed.
     * @param e A PropertyChangeEvent object describing the event source
     *       and the property that has changed.
     */
    public void propertyChange(final PropertyChangeEvent e) {
        final String propertyName = e.getPropertyName();

        if ("enabled".equals(propertyName)) {
            fMenu.setEnabled(((Boolean)e.getNewValue()).booleanValue());
            return;
        }

        if (AccessibleContext.ACCESSIBLE_STATE_PROPERTY.equals(propertyName)) {
            // rdar://Problem/3553843
            // When an ACCESSIBLE_STATE_PROPERTY changes, it's always newValue == null and oldValue == state turned off
            // or newValue == state turned on and oldValue == null.  We only care about changes in the ENABLED
            // state, so only change the menu's enabled state when the value is AccessibleState.ENABLED
            if (e.getNewValue() == AccessibleState.ENABLED || e.getOldValue() == AccessibleState.ENABLED) {
                final Object newValue = e.getNewValue();
                fMenu.setEnabled(newValue == AccessibleState.ENABLED);
            }
            return;
    }

        if ("accelerator".equals(propertyName)) {
            fMenu.setAccelerator((KeyStroke)e.getNewValue());
            return;
        }

        if (AbstractButton.TEXT_CHANGED_PROPERTY.equals(propertyName)) {
            fMenu.setLabel((String)e.getNewValue());
            return;
        }

        if (AbstractButton.ICON_CHANGED_PROPERTY.equals(propertyName)) {
            fMenu.setIcon((Icon)e.getNewValue());
            return;
        }

        if (JComponent.TOOL_TIP_TEXT_KEY.equals(propertyName)) {
            fMenu.setToolTipText((String)e.getNewValue());
            return;
        }

        if (AquaMenuItemUI.IndeterminateListener.CLIENT_PROPERTY_KEY.equals(propertyName)) {
            fMenu.setIndeterminate(AquaMenuItemUI.IndeterminateListener.isIndeterminate((JMenuItem)e.getSource()));
            return;
        }
    }
}
