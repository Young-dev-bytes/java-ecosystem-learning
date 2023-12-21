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

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.*;

public class AquaTextAreaUI extends BasicTextAreaUI {
    public static ComponentUI createUI(final JComponent c) {
        return new AquaTextAreaUI();
    }

    public AquaTextAreaUI() {
        super();
    }

    AquaFocusHandler handler;
    @Override
    protected void installListeners() {
        super.installListeners();

        final JTextComponent c = getComponent();
        handler = new AquaFocusHandler();
        c.addFocusListener(handler);
        c.addPropertyChangeListener(handler);

        AquaUtilControlSize.addSizePropertyListener(c);
    }

    @Override
    protected void uninstallListeners() {
        final JTextComponent c = getComponent();

        AquaUtilControlSize.removeSizePropertyListener(c);

        c.removeFocusListener(handler);
        c.removePropertyChangeListener(handler);
        handler = null;

        super.uninstallListeners();
    }

    boolean oldDragState = false;
    @Override
    protected void installDefaults() {
        if (!GraphicsEnvironment.isHeadless()) {
            oldDragState = getComponent().getDragEnabled();
            getComponent().setDragEnabled(true);
        }
        super.installDefaults();
    }

    @Override
    protected void uninstallDefaults() {
        if (!GraphicsEnvironment.isHeadless()) {
            getComponent().setDragEnabled(oldDragState);
        }
        super.uninstallDefaults();
    }

    // Install a default keypress action which handles Cmd and Option keys
    // properly
    @Override
    protected void installKeyboardActions() {
        super.installKeyboardActions();
        AquaKeyBindings bindings = AquaKeyBindings.instance();
        bindings.setDefaultAction(getKeymapName());
        final JTextComponent c = getComponent();
        bindings.installAquaUpDownActions(c);
    }

    @Override
    protected Caret createCaret() {
        return new AquaCaret();
    }

    @Override
    protected Highlighter createHighlighter() {
        return new AquaHighlighter();
    }
}
