/*
 * Copyright (c) 2011, 2015, Oracle and/or its affiliates. All rights reserved.
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

package sun.java2d.opengl;

import sun.lwawt.macosx.CFRetainedResource;
import sun.lwawt.LWWindowPeer;

import sun.java2d.SurfaceData;
import sun.java2d.NullSurfaceData;

import sun.awt.CGraphicsConfig;

import java.awt.Rectangle;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;

public class CGLLayer extends CFRetainedResource {

    private native long nativeCreateLayer();
    private static native void nativeSetScale(long layerPtr, double scale);
    private static native void validate(long layerPtr, CGLSurfaceData cglsd);
    private static native void blitTexture(long layerPtr);

    private LWWindowPeer peer;
    private int scale = 1;

    private SurfaceData surfaceData; // represents intermediate buffer (texture)

    public CGLLayer(LWWindowPeer peer) {
        super(0, true);

        setPtr(nativeCreateLayer());
        this.peer = peer;
    }

    public long getPointer() {
        return ptr;
    }

    public Rectangle getBounds() {
        return peer.getBounds();
    }

    public GraphicsConfiguration getGraphicsConfiguration() {
        return peer.getGraphicsConfiguration();
    }

    public boolean isOpaque() {
        return !peer.isTranslucent();
    }

    public int getTransparency() {
        return isOpaque() ? Transparency.OPAQUE : Transparency.TRANSLUCENT;
    }

    public Object getDestination() {
        return peer.getTarget();
    }

    public SurfaceData replaceSurfaceData() {
        if (getBounds().isEmpty()) {
            surfaceData = NullSurfaceData.theInstance;
            return surfaceData;
        }

        // the layer redirects all painting to the buffer's graphics
        // and blits the buffer to the layer surface (in drawInCGLContext callback)
        CGraphicsConfig gc = (CGraphicsConfig)getGraphicsConfiguration();
        surfaceData = gc.createSurfaceData(this);
        setScale(gc.getDevice().getScaleFactor());
        // the layer holds a reference to the buffer, which in
        // turn has a reference back to this layer
        if (surfaceData instanceof CGLSurfaceData) {
            validate((CGLSurfaceData)surfaceData);
        }

        return surfaceData;
    }

    public SurfaceData getSurfaceData() {
        return surfaceData;
    }

    public void validate(final CGLSurfaceData cglsd) {
        OGLRenderQueue rq = OGLRenderQueue.getInstance();
        rq.lock();
        try {
            execute(ptr -> validate(ptr, cglsd));
        } finally {
            rq.unlock();
        }
    }

    @Override
    public void dispose() {
        // break the connection between the layer and the buffer
        validate(null);
        super.dispose();
    }

    private void setScale(final int _scale) {
        if (scale != _scale) {
            scale = _scale;
            execute(ptr -> nativeSetScale(ptr, scale));
        }
    }

    // ----------------------------------------------------------------------
    // NATIVE CALLBACKS
    // ----------------------------------------------------------------------

    private void drawInCGLContext() {
        // tell the flusher thread not to update the intermediate buffer
        // until we are done blitting from it
        OGLRenderQueue rq = OGLRenderQueue.getInstance();
        rq.lock();
        try {
            execute(ptr -> blitTexture(ptr));
        } finally {
            rq.unlock();
        }
    }
}
