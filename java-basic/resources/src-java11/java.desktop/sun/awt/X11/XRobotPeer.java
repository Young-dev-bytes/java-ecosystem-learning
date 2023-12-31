/*
 * Copyright (c) 2003, 2019, Oracle and/or its affiliates. All rights reserved.
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

package sun.awt.X11;

import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.peer.RobotPeer;
import java.security.AccessController;

import sun.awt.AWTAccessor;
import sun.awt.SunToolkit;
import sun.awt.UNIXToolkit;
import sun.awt.X11GraphicsConfig;
import sun.security.action.GetPropertyAction;

final class XRobotPeer implements RobotPeer {

    static final boolean tryGtk;
    static {
        loadNativeLibraries();
        tryGtk = Boolean.parseBoolean(
                            AccessController.doPrivileged(
                                    new GetPropertyAction("awt.robot.gtk", "true")
                            ));
    }
    private static volatile boolean useGtk;
    private X11GraphicsConfig   xgc = null;

    /*
     * native implementation uses some static shared data (pipes, processes)
     * so use a class lock to synchronize native method calls
     */
    static Object robotLock = new Object();

    XRobotPeer(GraphicsConfiguration gc) {
        this.xgc = (X11GraphicsConfig)gc;
        SunToolkit tk = (SunToolkit)Toolkit.getDefaultToolkit();
        setup(tk.getNumberOfButtons(),
                AWTAccessor.getInputEventAccessor().getButtonDownMasks());

        boolean isGtkSupported = false;
        if (tryGtk) {
            if (tk instanceof UNIXToolkit && ((UNIXToolkit) tk).loadGTK()) {
                isGtkSupported = true;
            }
        }

        useGtk = (tryGtk && isGtkSupported);
    }

    @Override
    public void mouseMove(int x, int y) {
        mouseMoveImpl(xgc, xgc.scaleUp(x), xgc.scaleUp(y));
    }

    @Override
    public void mousePress(int buttons) {
        mousePressImpl(buttons);
    }

    @Override
    public void mouseRelease(int buttons) {
        mouseReleaseImpl(buttons);
    }

    @Override
    public void mouseWheel(int wheelAmt) {
        mouseWheelImpl(wheelAmt);
    }

    @Override
    public void keyPress(int keycode) {
        keyPressImpl(keycode);
    }

    @Override
    public void keyRelease(int keycode) {
        keyReleaseImpl(keycode);
    }

    @Override
    public int getRGBPixel(int x, int y) {
        int pixelArray[] = new int[1];
        getRGBPixelsImpl(xgc, x, y, 1, 1, pixelArray, useGtk);
        return pixelArray[0];
    }

    @Override
    public int [] getRGBPixels(Rectangle bounds) {
        int pixelArray[] = new int[bounds.width*bounds.height];
        getRGBPixelsImpl(xgc, bounds.x, bounds.y, bounds.width, bounds.height,
                            pixelArray, useGtk);
        return pixelArray;
    }

    private static synchronized native void setup(int numberOfButtons, int[] buttonDownMasks);
    private static native void loadNativeLibraries();

    private static synchronized native void mouseMoveImpl(X11GraphicsConfig xgc, int x, int y);
    private static synchronized native void mousePressImpl(int buttons);
    private static synchronized native void mouseReleaseImpl(int buttons);
    private static synchronized native void mouseWheelImpl(int wheelAmt);

    private static synchronized native void keyPressImpl(int keycode);
    private static synchronized native void keyReleaseImpl(int keycode);

    private static synchronized native void getRGBPixelsImpl(X11GraphicsConfig xgc,
            int x, int y, int width, int height, int pixelArray[], boolean isGtkSupported);
}
