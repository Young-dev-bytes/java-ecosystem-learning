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

import java.awt.Font;

import javax.swing.*;

// interface to let the Screen Menu classes share a JComponent PropertyChangeListener
interface ScreenMenuPropertyHandler {
    public void setEnabled(boolean b);
    public void setFont(Font f);
    public void setLabel(String f);
    public void setIcon(Icon icon);
    public void setAccelerator(KeyStroke ks);
    public void setToolTipText(String tooltip);
    public void setChildVisible(javax.swing.JMenuItem child, boolean b);
    public void setIndeterminate(boolean indeterminate);
}
