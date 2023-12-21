/*
 * Copyright (c) 2011, 2013, Oracle and/or its affiliates. All rights reserved.
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


package sun.lwawt;

import java.awt.Label;
import java.awt.peer.LabelPeer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Lightweight implementation of {@link LabelPeer}. Delegates most of the work
 * to the {@link JLabel}.
 */
final class LWLabelPeer extends LWComponentPeer<Label, JLabel>
        implements LabelPeer {

    LWLabelPeer(final Label target, final PlatformComponent platformComponent) {
        super(target, platformComponent);
    }

    @Override
    JLabel createDelegate() {
        return new JLabel();
    }

    @Override
    void initializeImpl() {
        super.initializeImpl();
        setText(getTarget().getText());
        setAlignment(getTarget().getAlignment());
    }

    @Override
    public void setText(final String label) {
        synchronized (getDelegateLock()) {
            getDelegate().setText(label);
        }
    }

    @Override
    public void setAlignment(final int alignment) {
        synchronized (getDelegateLock()) {
            getDelegate().setHorizontalAlignment(convertAlignment(alignment));
        }
    }

    /**
     * Converts {@code Label} alignment constant to the {@code JLabel} constant.
     * If wrong Label alignment provided returns default alignment.
     *
     * @param alignment {@code Label} constant.
     *
     * @return {@code JLabel} constant.
     */
    private static int convertAlignment(final int alignment) {
        switch (alignment) {
            case Label.CENTER:
                return SwingConstants.CENTER;
            case Label.RIGHT:
                return SwingConstants.RIGHT;
            default:
                return SwingConstants.LEFT;
        }
    }
}
