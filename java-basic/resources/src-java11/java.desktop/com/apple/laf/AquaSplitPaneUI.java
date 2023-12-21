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
import java.beans.*;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.*;

public class AquaSplitPaneUI extends BasicSplitPaneUI implements MouseListener, PropertyChangeListener {
    static final String DIVIDER_PAINTER_KEY = "JSplitPane.dividerPainter";

    public AquaSplitPaneUI() {
        super();
    }

    public static ComponentUI createUI(final JComponent x) {
        return new AquaSplitPaneUI();
    }

    public BasicSplitPaneDivider createDefaultDivider() {
        return new AquaSplitPaneDividerUI(this);
    }

    protected void installListeners() {
        super.installListeners();
        splitPane.addPropertyChangeListener(DIVIDER_PAINTER_KEY, this);
        divider.addMouseListener(this);
    }

    protected void uninstallListeners() {
        divider.removeMouseListener(this);
        splitPane.removePropertyChangeListener(DIVIDER_PAINTER_KEY, this);
        super.uninstallListeners();
    }

    public void mouseClicked(final MouseEvent e) {
        if (e.getClickCount() < 2) return;
        if (!splitPane.isOneTouchExpandable()) return;

        final double resizeWeight = splitPane.getResizeWeight();
        final int paneWidth = splitPane.getWidth();
        final int divSize = splitPane.getDividerSize();
        final int divLocation = splitPane.getDividerLocation();
        final int lastDivLocation = splitPane.getLastDividerLocation();

        // if we are at the far edge
        if (paneWidth - divSize <= divLocation + 5) {
            splitPane.setDividerLocation(lastDivLocation);
            return;
        }

        // if we are at the starting edge
        if (divSize >= divLocation - 5) {
            splitPane.setDividerLocation(lastDivLocation);
            return;
        }

        // otherwise, jump to the most "appropriate" end
        if (resizeWeight > 0.5) {
            splitPane.setDividerLocation(0);
        } else {
            splitPane.setDividerLocation(paneWidth);
        }
    }

    public void mouseEntered(final MouseEvent e) { }
    public void mouseExited(final MouseEvent e) { }
    public void mousePressed(final MouseEvent e) { }
    public void mouseReleased(final MouseEvent e) { }

    public void propertyChange(final PropertyChangeEvent evt) {
        if (!DIVIDER_PAINTER_KEY.equals(evt.getPropertyName())) return;

        final Object value = evt.getNewValue();
        if (value instanceof Border) {
            divider.setBorder((Border)value);
        } else {
            divider.setBorder(null);
        }
    }
}
