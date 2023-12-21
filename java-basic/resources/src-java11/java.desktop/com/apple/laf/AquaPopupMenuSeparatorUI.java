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
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

import com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;

public class AquaPopupMenuSeparatorUI extends BasicSeparatorUI {
    private static final RecyclableSingletonFromDefaultConstructor<AquaPopupMenuSeparatorUI> instance = new RecyclableSingletonFromDefaultConstructor<AquaPopupMenuSeparatorUI>(AquaPopupMenuSeparatorUI.class);

    public static ComponentUI createUI(final JComponent c) {
        return instance.get();
    }

    public void update(final Graphics g, final JComponent c) {
        paint(g, c);
    }

    public void paint(final Graphics g, final JComponent c) {
        final Dimension s = c.getSize();

        if (((JSeparator)c).getOrientation() == SwingConstants.VERTICAL) {
            g.setColor(c.getForeground());
            g.drawLine(5, 1, 5, s.height - 2);

            g.setColor(c.getBackground());
            g.drawLine(6, 1, 6, s.height - 2);
        } else {
            g.setColor(c.getForeground());
            g.drawLine(1, 5, s.width - 2, 5);

            g.setColor(c.getBackground());
            g.drawLine(1, 6, s.width - 2, 6);
        }
    }

    public Dimension getPreferredSize(final JComponent c) {
        if (((JSeparator)c).getOrientation() == SwingConstants.VERTICAL) {
            return new Dimension(12, 0);
        }

        return new Dimension(0, 12);
    }
}
