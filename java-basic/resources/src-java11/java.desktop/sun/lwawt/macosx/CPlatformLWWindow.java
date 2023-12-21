/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.FocusEvent;

import sun.awt.CGraphicsDevice;
import sun.awt.CGraphicsEnvironment;
import sun.awt.LightweightFrame;
import sun.java2d.SurfaceData;
import sun.lwawt.LWLightweightFramePeer;
import sun.lwawt.LWWindowPeer;
import sun.lwawt.PlatformWindow;

public class CPlatformLWWindow extends CPlatformWindow {

    @Override
    public void initialize(Window target, LWWindowPeer peer, PlatformWindow owner) {
        initializeBase(target, peer, owner);
    }

    @Override
    CPlatformView createContentView() {
        return new CPlatformLWView();
    }

    @Override
    public void toggleFullScreen() {
    }

    @Override
    public void setMenuBar(MenuBar mb) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public Point getLocationOnScreen() {
        return null;
    }

    @Override
    public SurfaceData getScreenSurface() {
        return null;
    }

    @Override
    public SurfaceData replaceSurfaceData() {
        return null;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        if (getPeer() != null) {
            getPeer().notifyReshape(x, y, w, h);
        }
    }

    @Override
    public void setVisible(boolean visible) {
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public void updateIconImages() {
    }

    @Override
    public SurfaceData getSurfaceData() {
        return null;
    }

    @Override
    public void toBack() {
    }

    @Override
    public void toFront() {
    }

    @Override
    public void setResizable(final boolean resizable) {
    }

    @Override
    public void setSizeConstraints(int minW, int minH, int maxW, int maxH) {
    }

    @Override
    public boolean rejectFocusRequest(FocusEvent.Cause cause) {
        return false;
    }

    @Override
    public boolean requestWindowFocus() {
        return true;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void updateFocusableWindowState() {
    }

    @Override
    public void setAlwaysOnTop(boolean isAlwaysOnTop) {
    }

    @Override
    public void setOpacity(float opacity) {
    }

    @Override
    public void setOpaque(boolean isOpaque) {
    }

    @Override
    public void enterFullScreenMode() {
    }

    @Override
    public void exitFullScreenMode() {
    }

    @Override
    public void setWindowState(int windowState) {
    }

    @Override
    public LWWindowPeer getPeer() {
        return super.getPeer();
    }

    @Override
    public CPlatformView getContentView() {
        return super.getContentView();
    }

    @Override
    public long getLayerPtr() {
        return 0;
    }

    @Override
    public GraphicsDevice getGraphicsDevice() {
        CGraphicsEnvironment ge = (CGraphicsEnvironment)GraphicsEnvironment.
                                  getLocalGraphicsEnvironment();

        LWLightweightFramePeer peer = (LWLightweightFramePeer)getPeer();
        int scale =(int) Math.round(((LightweightFrame)peer.getTarget())
                                                            .getScaleFactorX());

        Rectangle bounds = ((LightweightFrame)peer.getTarget()).getHostBounds();
        for (GraphicsDevice d : ge.getScreenDevices()) {
            if (d.getDefaultConfiguration().getBounds().intersects(bounds) &&
                ((CGraphicsDevice)d).getScaleFactor() == scale)
            {
                return d;
            }
        }
        // We shouldn't be here...
        return ge.getDefaultScreenDevice();
    }
}
