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
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.peer.CanvasPeer;

import javax.swing.JComponent;

/**
 * Lightweight implementation of {@link CanvasPeer}. This peer is empty, because
 * all the components in lwawt use graphic object from the top level window.
 */
class LWCanvasPeer<T extends Component, D extends JComponent>
        extends LWComponentPeer<T, D> implements CanvasPeer {

    LWCanvasPeer(final T target, final PlatformComponent platformComponent) {
        super(target, platformComponent);
    }

    @Override
    public final GraphicsConfiguration getAppropriateGraphicsConfiguration(
            final GraphicsConfiguration gc) {
        // TODO
        return gc;
    }

    @Override
    public final Dimension getPreferredSize() {
        return getMinimumSize();
    }

    @Override
    public final Dimension getMinimumSize() {
        return getBounds().getSize();
    }
}
