/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
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
import javax.swing.border.*;
import javax.swing.plaf.UIResource;

import apple.laf.JRSUIState;
import apple.laf.JRSUIConstants.*;

import com.apple.laf.AquaUtils.RecyclableSingleton;

@SuppressWarnings("serial") // Superclass is not serializable across versions
public class AquaTableHeaderBorder extends AbstractBorder {
    protected static final int SORT_NONE = 0;
    protected static final int SORT_ASCENDING = 1;
    protected static final int SORT_DECENDING = -1;

    protected final Insets editorBorderInsets = new Insets(1, 3, 1, 3);
    protected final AquaPainter<JRSUIState> painter = AquaPainter.create(JRSUIState.getInstance());

    protected static AquaTableHeaderBorder getListHeaderBorder() {
        // we don't want to share this, because the .setSelected() state
        // would persist to all other JTable instances
        return new AquaTableHeaderBorder();
    }

    protected AquaTableHeaderBorder() {
        painter.state.set(AlignmentHorizontal.LEFT);
        painter.state.set(AlignmentVertical.TOP);
    }

    /**
     * Paints the border for the specified component with the specified
     * position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    protected boolean doPaint = true;
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        if (!doPaint) return;
        final JComponent jc = (JComponent)c;

        // if the developer wants to set their own color, we should
        // interpret this as "get out of the way", and don't draw aqua.
        final Color componentBackground = jc.getBackground();
        if (!(componentBackground instanceof UIResource)) {
            doPaint = false;
            jc.paint(g);
            getAlternateBorder().paintBorder(jc, g, x, y, width, height);
            doPaint = true;
            return;
        }

        final State state = getState(jc);
        painter.state.set(state);
        painter.state.set(jc.hasFocus() ? Focused.YES : Focused.NO);
        painter.state.set(height > 16 ? Widget.BUTTON_BEVEL : Widget.BUTTON_LIST_HEADER);
        painter.state.set(selected ? BooleanValue.YES : BooleanValue.NO);

        switch (sortOrder) {
            case SORT_ASCENDING:
                painter.state.set(Direction.UP);
                break;
            case SORT_DECENDING:
                painter.state.set(Direction.DOWN);
                break;
            default:
                painter.state.set(Direction.NONE);
                break;
        }

        final int newX = x;
        final int newY = y;
        final int newWidth = width;
        final int newHeight = height;

        painter.paint(g, c, newX - 1, newY - 1, newWidth + 1, newHeight);

        // Draw the header
        g.clipRect(newX, y, newWidth, height);
        g.translate(fHorizontalShift, -1);
        doPaint = false;
        jc.paint(g);
        doPaint = true;
    }

    protected State getState(final JComponent jc) {
        if (!jc.isEnabled()) return State.DISABLED;

        final JRootPane rootPane = jc.getRootPane();
        if (rootPane == null) return State.ACTIVE;

        if (!AquaFocusHandler.isActive(rootPane)) return State.INACTIVE;

        return State.ACTIVE;
    }

    private static final RecyclableSingleton<Border> alternateBorder = new RecyclableSingleton<Border>() {
        @Override
        protected Border getInstance() {
            return BorderFactory.createRaisedBevelBorder();
        }
    };
    protected static Border getAlternateBorder() {
        return alternateBorder.get();
    }

    /**
     * Returns the insets of the border.
     * @param c the component for which this border insets value applies
     */
    public Insets getBorderInsets(final Component c) {
        // bad to create new one each time. For debugging only.
        return editorBorderInsets;
    }

    public Insets getBorderInsets(final Component c, final Insets insets) {
        insets.left = editorBorderInsets.left;
        insets.top = editorBorderInsets.top;
        insets.right = editorBorderInsets.right;
        insets.bottom = editorBorderInsets.bottom;
        return insets;
    }

    /**
     * Returns whether or not the border is opaque.  If the border
     * is opaque, it is responsible for filling in it's own
     * background when painting.
     */
    public boolean isBorderOpaque() {
        return false;
    }

    /**
     * Sets whether or not this instance of Border draws selected or not.  Used by AquaFileChooserUI
     */
    private boolean selected = false;
    protected void setSelected(final boolean inSelected) {
        selected = inSelected;
    }

    /**
     * Sets an amount to shift the position of the labels.  Used by AquaFileChooserUI
     */
    private int fHorizontalShift = 0;
    protected void setHorizontalShift(final int inShift) {
        fHorizontalShift = inShift;
    }

    private int sortOrder = SORT_NONE;
    protected void setSortOrder(final int inSortOrder) {
        if (inSortOrder < SORT_DECENDING || inSortOrder > SORT_ASCENDING) {
            throw new IllegalArgumentException("Invalid sort order constant: " + inSortOrder);
        }

        sortOrder = inSortOrder;
    }
}
