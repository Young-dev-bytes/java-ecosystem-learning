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
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.apple.laf.AquaUtils.RecyclableSingleton;
import com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;

public class AquaMnemonicHandler {
    private static final RecyclableSingleton<AltProcessor> altProcessor = new RecyclableSingletonFromDefaultConstructor<AltProcessor>(AltProcessor.class);
    public static KeyEventPostProcessor getInstance() {
        return altProcessor.get();
    }

    protected static boolean isMnemonicHidden = true; // true by default

    public static void setMnemonicHidden(final boolean hide) {
        if (UIManager.getBoolean("Button.showMnemonics")) {
            // Do not hide mnemonics if the UI defaults do not support this
            isMnemonicHidden = false;
        } else {
            isMnemonicHidden = hide;
        }
    }

    /**
     * Gets the state of the hide mnemonic flag. This only has meaning if this feature is supported by the underlying OS.
     *
     * @return true if mnemonics are hidden, otherwise, false
     * @see #setMnemonicHidden
     * @since 1.4
     */
    public static boolean isMnemonicHidden() {
        if (UIManager.getBoolean("Button.showMnemonics")) {
            // Do not hide mnemonics if the UI defaults do not support this
            isMnemonicHidden = false;
        }
        return isMnemonicHidden;
    }

    static class AltProcessor implements KeyEventPostProcessor {
        public boolean postProcessKeyEvent(final KeyEvent ev) {
            if (ev.getKeyCode() != KeyEvent.VK_ALT) {
                return false;
            }

            final JRootPane root = SwingUtilities.getRootPane(ev.getComponent());
            final Window winAncestor = (root == null ? null : SwingUtilities.getWindowAncestor(root));

            switch(ev.getID()) {
                case KeyEvent.KEY_PRESSED:
                    setMnemonicHidden(false);
                    break;
                case KeyEvent.KEY_RELEASED:
                    setMnemonicHidden(true);
                    break;
            }

            repaintMnemonicsInWindow(winAncestor);

            return false;
        }
    }

    /*
     * Repaints all the components with the mnemonics in the given window and all its owned windows.
     */
    static void repaintMnemonicsInWindow(final Window w) {
        if (w == null || !w.isShowing()) {
            return;
        }

        final Window[] ownedWindows = w.getOwnedWindows();
        for (final Window element : ownedWindows) {
            repaintMnemonicsInWindow(element);
        }

        repaintMnemonicsInContainer(w);
    }

    /*
     * Repaints all the components with the mnemonics in container.
     * Recursively searches for all the subcomponents.
     */
    static void repaintMnemonicsInContainer(final Container cont) {
        for (int i = 0; i < cont.getComponentCount(); i++) {
            final Component c = cont.getComponent(i);
            if (c == null || !c.isVisible()) {
                continue;
            }

            if (c instanceof AbstractButton && ((AbstractButton)c).getMnemonic() != '\0') {
                c.repaint();
                continue;
            }

            if (c instanceof JLabel && ((JLabel)c).getDisplayedMnemonic() != '\0') {
                c.repaint();
                continue;
            }

            if (c instanceof Container) {
                repaintMnemonicsInContainer((Container)c);
            }
        }
    }
}
