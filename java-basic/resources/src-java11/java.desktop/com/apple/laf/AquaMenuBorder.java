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

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;

public class AquaMenuBorder implements Border, UIResource {
    public AquaMenuBorder() { }

    /**
     * Paints the border for the specified component with the specified
     * position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        // for now we don't paint a border. We let the button paint it since there
        // needs to be a strict ordering for aqua components.
        //paintButton(c, g, x, y, width, height);
        //if (c instanceof JPopupMenu) {
            //g.setColor(Color.red);
            //g.drawRect(x,y, width-1, height-1);
        //}
    }

    /**
     * Returns whether or not the border is opaque.  If the border
     * is opaque, it is responsible for filling in it's own
     * background when painting.
     */
    public boolean isBorderOpaque() {
        return false;
    }

    protected static Insets getItemInsets() {
        return new Insets(1, 5, 1, 5);
    }

    protected static Insets getEmptyInsets() {
        return new Insets(0, 0, 0, 0);
    }

    protected static Insets getPopupInsets() {
        return new Insets(4, 0, 4, 0);
    }

    /**
     * Returns the insets of the border.
     * @param c the component for which this border insets value applies
     */
    public Insets getBorderInsets(final Component c) {
        if (!(c instanceof JPopupMenu)) {
            return getItemInsets();
        }

        // for more info on this issue, see AquaComboBoxPopup.updateContents()
        final JPopupMenu menu = (JPopupMenu)c;
        final int nChildren = menu.getComponentCount();
        if (nChildren > 0) {
            final Component firstChild = menu.getComponent(0);
            if (firstChild instanceof Box.Filler) return getEmptyInsets();
            if (firstChild instanceof JScrollPane) return getEmptyInsets();
        }

        // just need top and bottom, and not right and left.
        // but only for non-list popups.
        return getPopupInsets();
    }
}
