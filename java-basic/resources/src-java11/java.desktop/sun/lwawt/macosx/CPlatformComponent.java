/*
 * Copyright (c) 2011, 2016, Oracle and/or its affiliates. All rights reserved.
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


package sun.lwawt.macosx;

import java.awt.Insets;

import sun.lwawt.PlatformComponent;
import sun.lwawt.PlatformWindow;

/**
 * On OSX {@code CPlatformComponent} stores pointer to the native CAlayer which
 * can be used from JAWT.
 */
class CPlatformComponent extends CFRetainedResource
        implements PlatformComponent {

    private volatile PlatformWindow platformWindow;

    CPlatformComponent() {
        super(0, true);
    }

    /**
     * Used by JAWT.
     */
    public long getPointer() {
        return ptr;
    }

    @Override
    public void initialize(final PlatformWindow platformWindow) {
        this.platformWindow = platformWindow;
        setPtr(nativeCreateComponent(platformWindow.getLayerPtr()));
    }

    // TODO: visibility, z-order

    @Override
    public void setBounds(final int x, final int y, final int w, final int h) {
        // translates values from the coordinate system of the top-level window
        // to the coordinate system of the content view
        final Insets insets = platformWindow.getPeer().getInsets();
        execute(ptr->nativeSetBounds(ptr, x - insets.left, y - insets.top, w, h));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private native long nativeCreateComponent(long windowLayer);

    private native void nativeSetBounds(long ptr, int x, int y, int w, int h);
}
