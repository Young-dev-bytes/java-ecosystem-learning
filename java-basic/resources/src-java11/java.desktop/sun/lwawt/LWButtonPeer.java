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

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;

import javax.swing.JButton;

/**
 * Lightweight implementation of {@link ButtonPeer}. Delegates most of the work
 * to the {@link JButton}.
 */
final class LWButtonPeer extends LWComponentPeer<Button, JButton>
        implements ButtonPeer, ActionListener {

    LWButtonPeer(final Button target,
                 final PlatformComponent platformComponent) {
        super(target, platformComponent);
    }

    @Override
    JButton createDelegate() {
        return new JButtonDelegate();
    }

    @Override
    void initializeImpl() {
        super.initializeImpl();
        setLabel(getTarget().getLabel());
        synchronized (getDelegateLock()) {
            getDelegate().addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        postEvent(new ActionEvent(getTarget(), ActionEvent.ACTION_PERFORMED,
                                  getTarget().getActionCommand(), e.getWhen(),
                                  e.getModifiers()));
    }

    @Override
    public void setLabel(final String label) {
        synchronized (getDelegateLock()) {
            getDelegate().setText(label);
        }
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @SuppressWarnings("serial")// Safe: outer class is non-serializable.
    private final class JButtonDelegate extends JButton {

        // Empty non private constructor was added because access to this
        // class shouldn't be emulated by a synthetic accessor method.
        JButtonDelegate() {
            super();
        }

        @Override
        public boolean hasFocus() {
            return getTarget().hasFocus();
        }
    }
}
