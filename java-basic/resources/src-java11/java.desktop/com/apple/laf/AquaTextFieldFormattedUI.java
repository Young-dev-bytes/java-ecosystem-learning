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

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;

/**
 * This class exists only as a hack to work around a Sun bug which parks the
 * insertion caret at the begining of a text field when it gets clicked on.
 */
public class AquaTextFieldFormattedUI extends AquaTextFieldUI implements MouseListener {
    public static ComponentUI createUI(final JComponent c) {
        return new AquaTextFieldFormattedUI();
    }

    @Override
    protected String getPropertyPrefix() {
        return "FormattedTextField";
    }

    protected void installListeners() {
        super.installListeners();
        getComponent().addMouseListener(this);
    }

    protected void uninstallListeners() {
        getComponent().removeMouseListener(this);
        super.uninstallListeners();
    }

    @SuppressWarnings("deprecation")
    public void mouseClicked(final MouseEvent e) {
        if (e.getClickCount() != 1) return;

        final JTextComponent c = getComponent();
        // apparently, focus has already been granted by the time this mouse listener fires
    //    if (c.hasFocus()) return;

        c.setCaretPosition(viewToModel(c, e.getPoint()));
    }

    public void mouseEntered(final MouseEvent e) { }
    public void mouseExited(final MouseEvent e) { }
    public void mousePressed(final MouseEvent e) { }
    public void mouseReleased(final MouseEvent e) { }
}
