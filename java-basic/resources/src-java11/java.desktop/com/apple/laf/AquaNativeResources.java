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
import java.awt.image.BufferedImage;

import javax.swing.plaf.UIResource;

import com.apple.laf.AquaUtils.RecyclableSingleton;

public class AquaNativeResources {
    static {
        java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.loadLibrary("osxui");
                    return null;
                }
            });
    }

    // TODO: removing CColorPaint for now
    @SuppressWarnings("serial") // JDK implementation class
    static class CColorPaintUIResource extends Color/*CColorPaint*/ implements UIResource {
        // The color passed to this MUST be a retained NSColor, and the CColorPaintUIResource
        //  takes ownership of that retain.
        public CColorPaintUIResource(long color, int r, int g, int b, int a) {
            super(r, g, b, a);
            //super(color, r, g, b, a);
        }
    }

    private static final RecyclableSingleton<Color> sBackgroundColor = new RecyclableSingleton<Color>() {
        @Override
        protected Color getInstance() {
            final long backgroundID = getWindowBackgroundColor();
            return new CColorPaintUIResource(backgroundID, 0xEE, 0xEE, 0xEE, 0xFF);
        }
    };
    private static native long getWindowBackgroundColor();
    public static Color getWindowBackgroundColorUIResource() {
        return sBackgroundColor.get();
    }

    static BufferedImage getRadioButtonSizerImage() {
        final BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB_PRE);

        Graphics g = img.getGraphics();
        g.setColor(Color.pink);
        g.fillRect(0, 0, 20, 20);
        g.dispose();

        return img;
    }
}
