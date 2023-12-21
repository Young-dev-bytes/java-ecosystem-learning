/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;

import apple.laf.JRSUIConstants.*;

/**
 * A Mac L&F implementation of JList
 *
 * All this does is look for a ThemeBorder and invalidate it when the focus changes
 */
public class AquaListUI extends BasicListUI {
    public static ComponentUI createUI(final JComponent c) {
        return new AquaListUI();
    }

    /**
     * Creates the focus listener to repaint the focus ring
     */
    protected FocusListener createFocusListener() {
        return new AquaListUI.FocusHandler();
    }

    /**
     * Creates a delegate that implements MouseInputListener.
     */
    protected MouseInputListener createMouseInputListener() {
        return new AquaListUI.MouseInputHandler();
    }

    protected void installKeyboardActions() {
        super.installKeyboardActions();
        list.getActionMap().put("aquaHome", new AquaHomeEndAction(true));
        list.getActionMap().put("aquaEnd", new AquaHomeEndAction(false));
    }

    @SuppressWarnings("serial") // Superclass is not serializable across versions
    static class AquaHomeEndAction extends AbstractAction {
        private boolean fHomeAction = false;

        protected AquaHomeEndAction(final boolean isHomeAction) {
            fHomeAction = isHomeAction;
        }

        /**
         * For a Home action, scrolls to the top. Otherwise, scroll to the end.
         */
        public void actionPerformed(final ActionEvent e) {
            final JList<?> list = (JList<?>)e.getSource();

            if (fHomeAction) {
                list.ensureIndexIsVisible(0);
            } else {
                final int size = list.getModel().getSize();
                list.ensureIndexIsVisible(size - 1);
            }
        }
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug. This class should be treated as a
     * &quot;protected&quot; inner class. Instantiate it only within subclasses of BasicListUI.
     */
    class FocusHandler extends BasicListUI.FocusHandler {
        public void focusGained(final FocusEvent e) {
            super.focusGained(e);
            AquaBorder.repaintBorder(getComponent());
        }

        public void focusLost(final FocusEvent e) {
            super.focusLost(e);
            AquaBorder.repaintBorder(getComponent());
        }
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        return new AquaPropertyChangeHandler();
    }

    class AquaPropertyChangeHandler extends PropertyChangeHandler {
        public void propertyChange(final PropertyChangeEvent e) {
            final String prop = e.getPropertyName();
            if (AquaFocusHandler.FRAME_ACTIVE_PROPERTY.equals(prop)) {
                AquaBorder.repaintBorder(getComponent());
                AquaFocusHandler.swapSelectionColors("List", getComponent(), e.getNewValue());
            } else {
                super.propertyChange(e);
            }
        }
    }

    // TODO: Using default handler for now, need to handle cmd-key

    // Replace the mouse event with one that returns the cmd-key state when asked
    // for the control-key state, which super assumes is what everyone does to discontiguously extend selections
    class MouseInputHandler extends BasicListUI.MouseInputHandler {
        /*public void mousePressed(final MouseEvent e) {
            super.mousePressed(new SelectionMouseEvent(e));
        }
        public void mouseDragged(final MouseEvent e) {
            super.mouseDragged(new SelectionMouseEvent(e));
        }*/
    }

    JList<Object> getComponent() {
        return list;
    }

    // this is used for blinking combobox popup selections when they are selected
    protected void repaintCell(final Object value, final int selectedIndex, final boolean selected) {
        final Rectangle rowBounds = getCellBounds(list, selectedIndex, selectedIndex);
        if (rowBounds == null) return;

        final ListCellRenderer<Object> renderer = list.getCellRenderer();
        if (renderer == null) return;

        final Component rendererComponent = renderer.getListCellRendererComponent(list, value, selectedIndex, selected, true);
        if (rendererComponent == null) return;

        final AquaComboBoxRenderer aquaRenderer = renderer instanceof AquaComboBoxRenderer ? (AquaComboBoxRenderer)renderer : null;
        if (aquaRenderer != null) aquaRenderer.setDrawCheckedItem(false);
        rendererPane.paintComponent(list.getGraphics().create(), rendererComponent, list, rowBounds.x, rowBounds.y, rowBounds.width, rowBounds.height, true);
        if (aquaRenderer != null) aquaRenderer.setDrawCheckedItem(true);
    }

    /*
    Insert note on JIDESoft naughtiness
    */
    public static Border getSourceListBackgroundPainter() {
        final AquaBorder border = new ComponentPainter();
        border.painter.state.set(Widget.GRADIENT);
        border.painter.state.set(Variant.GRADIENT_SIDE_BAR);
        return border;
    }

    public static Border getSourceListSelectionBackgroundPainter() {
        final AquaBorder border = new ComponentPainter();
        border.painter.state.set(Widget.GRADIENT);
        border.painter.state.set(Variant.GRADIENT_SIDE_BAR_SELECTION);
        return border;
    }

    public static Border getSourceListFocusedSelectionBackgroundPainter() {
        final AquaBorder border = new ComponentPainter();
        border.painter.state.set(Widget.GRADIENT);
        border.painter.state.set(Variant.GRADIENT_SIDE_BAR_FOCUSED_SELECTION);
        return border;
    }

    public static Border getListEvenBackgroundPainter() {
        final AquaBorder border = new ComponentPainter();
        border.painter.state.set(Widget.GRADIENT);
        border.painter.state.set(Variant.GRADIENT_LIST_BACKGROUND_EVEN);
        return border;
    }

    public static Border getListOddBackgroundPainter() {
        final AquaBorder border = new ComponentPainter();
        border.painter.state.set(Widget.GRADIENT);
        border.painter.state.set(Variant.GRADIENT_LIST_BACKGROUND_ODD);
        return border;
    }

    static class ComponentPainter extends AquaBorder.Default {
        public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int w, final int h) {
            final JComponent jc = c instanceof JComponent ? (JComponent)c : null;
            if (jc != null && !AquaFocusHandler.isActive(jc)) {
                painter.state.set(State.INACTIVE);
            } else {
                painter.state.set(State.ACTIVE);
            }
            super.paintBorder(c, g, x, y, w, h);
        }
    }
}
