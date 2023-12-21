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

package com.apple.laf;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;

/**
 * A Mac L&F implementation of JTable
 *
 * All this does is look for a ThemeBorder and invalidate it when the focus changes
 */
public class AquaTableUI extends BasicTableUI {
    public static ComponentUI createUI(final JComponent c) {
        return new AquaTableUI();
    }

    /**
     * Creates the focus listener to repaint the focus ring
     */
    protected FocusListener createFocusListener() {
        return new AquaTableUI.FocusHandler();
    }

    /**
     * Creates the mouse listener for the JTable.
     */
    protected MouseInputListener createMouseInputListener() {
        return new AquaTableUI.MouseInputHandler();
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of BasicTableUI.
     */
    public class FocusHandler extends BasicTableUI.FocusHandler {
        public void focusGained(final FocusEvent e) {
            super.focusGained(e);
            AquaBorder.repaintBorder(getComponent());
        }

        public void focusLost(final FocusEvent e) {
            super.focusLost(e);
            AquaBorder.repaintBorder(getComponent());
        }
    }

    protected AquaFocusHandler focusHandler = new AquaFocusHandler() {
        public void propertyChange(final PropertyChangeEvent ev) {
            super.propertyChange(ev);
            if (!FRAME_ACTIVE_PROPERTY.equals(ev.getPropertyName())) return;
            AquaFocusHandler.swapSelectionColors("Table", getComponent(), ev.getNewValue());
        }
    };
    protected void installListeners() {
        super.installListeners();
        table.addFocusListener(focusHandler);
        table.addPropertyChangeListener(focusHandler);
    }

    protected void uninstallListeners() {
        table.removePropertyChangeListener(focusHandler);
        table.removeFocusListener(focusHandler);
        super.uninstallListeners();
    }

    // TODO: Using default handler for now, need to handle cmd-key

    // Replace the mouse event with one that returns the cmd-key state when asked
    // for the control-key state, which super assumes is what everyone does to discontiguously extend selections
    public class MouseInputHandler extends BasicTableUI.MouseInputHandler {
        /*public void mousePressed(final MouseEvent e) {
            super.mousePressed(new SelectionMouseEvent(e));
        }
        public void mouseDragged(final MouseEvent e) {
            super.mouseDragged(new SelectionMouseEvent(e));
        }*/
    }

    JTable getComponent() {
        return table;
    }
}
