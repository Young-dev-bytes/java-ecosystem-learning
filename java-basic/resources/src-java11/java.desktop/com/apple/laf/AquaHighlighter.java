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

//  Based on 1.3.1's AquaHighlighter, with the main difference that an inactive selection should be gray
//  rather than a darker version of the current highlight color.

package com.apple.laf;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.text.*;

import com.apple.laf.AquaUtils.RecyclableSingleton;

public class AquaHighlighter extends DefaultHighlighter implements UIResource {
    private static final RecyclableSingleton<LayerPainter> instance = new RecyclableSingleton<LayerPainter>() {
        protected LayerPainter getInstance() {
            return new AquaHighlightPainter(null);
        }
    };

    protected static LayeredHighlighter.LayerPainter getInstance() {
        return instance.get();
    }

    public static class AquaHighlightPainter extends DefaultHighlightPainter {
        Color selectionColor;
        Color disabledSelectionColor;

        public AquaHighlightPainter(final Color c) {
            super(c);
        }

        public Color getColor() {
            return selectionColor == null ? super.getColor() : selectionColor;
        }


        protected Color getInactiveSelectionColor() {
            if (disabledSelectionColor != null) return disabledSelectionColor;
            return disabledSelectionColor = UIManager.getColor("TextComponent.selectionBackgroundInactive");
        }

        void setColor(final JTextComponent c) {
            selectionColor = super.getColor();

            if (selectionColor == null) selectionColor = c.getSelectionColor();

            final Window owningWindow = SwingUtilities.getWindowAncestor(c);

            // If window is not currently active selection color is a gray with RGB of (212, 212, 212).
            if (owningWindow != null && !owningWindow.isActive()) {
                selectionColor = getInactiveSelectionColor();
            }

            if (!c.hasFocus()) {
                selectionColor = getInactiveSelectionColor();
            }
        }

        public void paint(final Graphics g, final int offs0, final int offs1, final Shape bounds, final JTextComponent c) {
            setColor(c);
            super.paint(g, offs0, offs1, bounds, c);
        }

        public Shape paintLayer(final Graphics g, final int offs0, final int offs1, final Shape bounds, final JTextComponent c, final View view) {
            setColor(c);
            return super.paintLayer(g, offs0, offs1, bounds, c, view);
        }
    }
}
