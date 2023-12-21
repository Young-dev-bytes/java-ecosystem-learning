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
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.*;

public class AquaEditorPaneUI extends BasicEditorPaneUI {
    public static ComponentUI createUI(final JComponent c){
        return new AquaEditorPaneUI();
    }

    boolean oldDragState = false;
    @Override
    protected void installDefaults(){
        super.installDefaults();
        if(!GraphicsEnvironment.isHeadless()){
            oldDragState = getComponent().getDragEnabled();
            getComponent().setDragEnabled(true);
        }
    }

    @Override
    protected void uninstallDefaults(){
        if(!GraphicsEnvironment.isHeadless()){
            getComponent().setDragEnabled(oldDragState);
        }
        super.uninstallDefaults();
    }

    FocusListener focusListener;
    @Override
    protected void installListeners(){
        super.installListeners();
        focusListener = createFocusListener();
        getComponent().addFocusListener(focusListener);
    }

    @Override
    protected void installKeyboardActions() {
        super.installKeyboardActions();
        AquaKeyBindings bindings = AquaKeyBindings.instance();
        bindings.setDefaultAction(getKeymapName());
        final JTextComponent c = getComponent();
        bindings.installAquaUpDownActions(c);
    }

    @Override
    protected void uninstallListeners(){
        getComponent().removeFocusListener(focusListener);
        super.uninstallListeners();
    }

    protected FocusListener createFocusListener(){
        return new AquaFocusHandler();
    }

    @Override
    protected Caret createCaret() {
        return new AquaCaret();
    }

    @Override
    protected Highlighter createHighlighter(){
        return new AquaHighlighter();
    }
}
