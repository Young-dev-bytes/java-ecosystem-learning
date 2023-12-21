/*
 * Copyright (c) 2011, 2018, Oracle and/or its affiliates. All rights reserved.
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

import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.Icon;
import javax.swing.AbstractButton;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.DefaultButtonModel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.plaf.ComponentUI;

import java.awt.Component;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;

import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import apple.laf.JRSUIConstants.Widget;
import com.apple.laf.AquaUtilControlSize.SizeVariant;
import com.apple.laf.AquaUtilControlSize.SizeDescriptor;
import com.apple.laf.AquaUtils.RecyclableSingleton;
import com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.Enumeration;

public class AquaButtonRadioUI extends AquaButtonLabeledUI {

    private static final RecyclableSingleton<AquaButtonRadioUI> instance = new RecyclableSingletonFromDefaultConstructor<AquaButtonRadioUI>(AquaButtonRadioUI.class);
    private static final RecyclableSingleton<ImageIcon> sizingIcon = new RecyclableSingleton<ImageIcon>() {
        protected ImageIcon getInstance() {
            return new ImageIcon(AquaNativeResources.getRadioButtonSizerImage());
        }
    };

    public static ComponentUI createUI(final JComponent b) {
        return instance.get();
    }

    public static Icon getSizingRadioButtonIcon() {
        return sizingIcon.get();
    }

    protected String getPropertyPrefix() {
        return "RadioButton" + ".";
    }

    protected AquaButtonBorder getPainter() {
        return new RadioButtonBorder();
    }

    public static class RadioButtonBorder extends LabeledButtonBorder {
        public RadioButtonBorder() {
            super(new SizeDescriptor(new SizeVariant().replaceMargins("RadioButton.margin")));
            painter.state.set(Widget.BUTTON_RADIO);
        }

        public RadioButtonBorder(final RadioButtonBorder other) {
            super(other);
        }
    }
}
