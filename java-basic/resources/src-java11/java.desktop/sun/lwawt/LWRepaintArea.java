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

import java.awt.Component;
import java.awt.Graphics;

import sun.awt.AWTAccessor;
import sun.awt.RepaintArea;

/**
 * Emulates appearance of heavyweight components before call of the user code.
 *
 * @author Sergey Bylokhov
 */
final class LWRepaintArea extends RepaintArea {

    @Override
    protected void updateComponent(final Component comp, final Graphics g) {
        // We shouldn't paint native component as a result of UPDATE events,
        // just flush onscreen back-buffer.
        if (comp != null) {
            super.updateComponent(comp, g);
            LWComponentPeer.flushOnscreenGraphics();
        }
    }

    @Override
    protected void paintComponent(final Component comp, final Graphics g) {
        if (comp != null) {
            Object peer = AWTAccessor.getComponentAccessor().getPeer(comp);
            if (peer != null) {
                ((LWComponentPeer<?, ?>) peer).paintPeer(g);
            }
            super.paintComponent(comp, g);
            LWComponentPeer.flushOnscreenGraphics();
        }
    }
}
