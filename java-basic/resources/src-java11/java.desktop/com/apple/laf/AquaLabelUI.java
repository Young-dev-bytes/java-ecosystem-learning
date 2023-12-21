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
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

import sun.swing.SwingUtilities2;

import com.apple.laf.AquaUtils.RecyclableSingleton;
import com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;

public class AquaLabelUI extends BasicLabelUI {
    private static final RecyclableSingleton<AquaLabelUI> aquaLabelUI = new RecyclableSingletonFromDefaultConstructor<AquaLabelUI>(AquaLabelUI.class);

    public static ComponentUI createUI(final JComponent c) {
        return aquaLabelUI.get();
    }

    protected void installListeners(final JLabel c) {
        super.installListeners(c);
        AquaUtilControlSize.addSizePropertyListener(c);
    }

    protected void uninstallListeners(final JLabel c) {
        AquaUtilControlSize.removeSizePropertyListener(c);
        super.uninstallListeners(c);
    }

    protected void paintEnabledText(final JLabel l, final Graphics g, final String s, final int textX, final int textY) {
        int mnemIndex = l.getDisplayedMnemonicIndex();
        if (AquaMnemonicHandler.isMnemonicHidden()) {
            mnemIndex = -1;
        }

        g.setColor(l.getForeground());
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY);
    }

    /**
     * Paint clippedText at textX, textY with background.lighter() and then
     * shifted down and to the right by one pixel with background.darker().
     *
     * @see #paint
     * @see #paintEnabledText
     */
    protected void paintDisabledText(final JLabel l, final Graphics g, final String s, final int textX, final int textY) {
        int accChar = l.getDisplayedMnemonicIndex();
        if (AquaMnemonicHandler.isMnemonicHidden()) {
            accChar = -1;
        }

        final Color background = l.getBackground();

        // if our background is still something we set then we can use our happy background color.
        if (background instanceof UIResource) {
            g.setColor(getDisabledLabelColor(l));
            SwingUtilities2.drawStringUnderlineCharAt(l, g, s, accChar, textX, textY);
        } else {
            super.paintDisabledText(l, g, s, textX, textY);
        }
    }

    static final String DISABLED_COLOR_KEY = "Label.disabledForegroundColor";
    protected Color getDisabledLabelColor(final JLabel label) {
        final Color fg = label.getForeground();

        final Object colorProperty = label.getClientProperty(DISABLED_COLOR_KEY);
        if (colorProperty instanceof Color) {
            final Color disabledColor = (Color)colorProperty;
            if ((fg.getRGB() << 8) == (disabledColor.getRGB() << 8)) return disabledColor;
        }

        final Color newDisabledColor = new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), fg.getAlpha() / 2);
        label.putClientProperty(DISABLED_COLOR_KEY, newDisabledColor);
        return newDisabledColor;
    }
}
