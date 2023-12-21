/*
 * Copyright (c) 2005, 2016, Oracle and/or its affiliates. All rights reserved.
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

package sun.java2d.loops;

import sun.java2d.loops.GraphicsPrimitive;
import sun.java2d.SunGraphics2D;
import sun.java2d.SurfaceData;
import sun.font.GlyphList;

/**
 *   DrawGlyphListLCD- loops for LCDTextRenderer pipe
 *   1) draw LCD anti-aliased text onto destination surface
 *   2) must accept output area [x, y, dx, dy]
 *      from within the surface description data for clip rect
 */
public class DrawGlyphListLCD extends GraphicsPrimitive {

    public static final String
        methodSignature = "DrawGlyphListLCD(...)".toString();

    public static final int primTypeID = makePrimTypeID();

    public static DrawGlyphListLCD locate(SurfaceType srctype,
                                           CompositeType comptype,
                                           SurfaceType dsttype)
    {
        return (DrawGlyphListLCD)
            GraphicsPrimitiveMgr.locate(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected DrawGlyphListLCD(SurfaceType srctype,
                                CompositeType comptype,
                                SurfaceType dsttype)
    {
        super(methodSignature, primTypeID, srctype, comptype, dsttype);
    }

    public DrawGlyphListLCD(long pNativePrim,
                             SurfaceType srctype,
                             CompositeType comptype,
                             SurfaceType dsttype)
    {
        super(pNativePrim, methodSignature, primTypeID,
              srctype, comptype, dsttype);
    }

    public native void DrawGlyphListLCD(SunGraphics2D sg2d, SurfaceData dest,
                                         GlyphList srcData);

    static {
        GraphicsPrimitiveMgr.registerGeneral(
                                   new DrawGlyphListLCD(null, null, null));
    }

    public GraphicsPrimitive makePrimitive(SurfaceType srctype,
                                           CompositeType comptype,
                                           SurfaceType dsttype) {
        /* Do not return a General loop. SunGraphics2D determines whether
         * to use LCD or standard AA text based on whether there is an
         * installed loop.
         * This can be uncommented once there is a General loop which can
         * handle one byte per colour component masks properly.
         */
        return null;
    }

    public GraphicsPrimitive traceWrap() {
        return new TraceDrawGlyphListLCD(this);
    }

    private static class TraceDrawGlyphListLCD extends DrawGlyphListLCD {
        DrawGlyphListLCD target;

        public TraceDrawGlyphListLCD(DrawGlyphListLCD target) {
            super(target.getSourceType(),
                  target.getCompositeType(),
                  target.getDestType());
            this.target = target;
        }

        public GraphicsPrimitive traceWrap() {
            return this;
        }

        public void DrawGlyphListLCD(SunGraphics2D sg2d, SurfaceData dest,
                                      GlyphList glyphs)
        {
            tracePrimitive(target);
            target.DrawGlyphListLCD(sg2d, dest, glyphs);
        }
    }
}
