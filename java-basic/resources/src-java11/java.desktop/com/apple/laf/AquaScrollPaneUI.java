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

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;

public class AquaScrollPaneUI extends javax.swing.plaf.basic.BasicScrollPaneUI {
    public static ComponentUI createUI(final JComponent x) {
        return new AquaScrollPaneUI();
    }

    protected MouseWheelListener createMouseWheelListener() {
        return new XYMouseWheelHandler();
    }

    protected class XYMouseWheelHandler extends BasicScrollPaneUI.MouseWheelHandler {
        public void mouseWheelMoved(final MouseWheelEvent e) {
            super.mouseWheelMoved(e);
            // Consume the event even when the scrollBar is invisible
            // see #7124320
            e.consume();
        }
    }
}
