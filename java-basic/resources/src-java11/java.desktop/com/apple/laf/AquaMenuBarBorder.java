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

package com.apple.laf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.plaf.UIResource;

import sun.swing.SwingUtilities2;

/**
 * The class represents the border of a {@code JMenuBar}.
 */
public class AquaMenuBarBorder implements Border, UIResource {

    @Override
    public void paintBorder(final Component c, final Graphics g, final int x,
                            final int y, final int width, final int height) {
        g.setColor(Color.gray);
        SwingUtilities2.drawHLine(g, x, x + width - 1, y + height - 1);
    }

    @Override
    public Insets getBorderInsets(final Component c) {
        return new Insets(0, 0, 1, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
