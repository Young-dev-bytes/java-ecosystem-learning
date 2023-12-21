/*
 * Copyright (c) 2004, 2016, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Composite;
import java.awt.geom.AffineTransform;
import sun.java2d.SurfaceData;
import sun.java2d.loops.GraphicsPrimitive;
import sun.java2d.pipe.Region;

/**
 * TransformHelper
 * 1) applies an AffineTransform to a rectangle of pixels while copying
 *    from one surface to another
 * 2) performs compositing of colors based upon a Composite
 *    parameter
 *
 * precise behavior is undefined if the source surface
 * and the destination surface are the same surface
 * with overlapping regions of pixels
 */
public class TransformHelper extends GraphicsPrimitive
{
    public static final String methodSignature =
        "TransformHelper(...)".toString();

    public static final int primTypeID = makePrimTypeID();

    private static RenderCache helpercache = new RenderCache(10);

    public static TransformHelper locate(SurfaceType srctype) {
        return (TransformHelper)
            GraphicsPrimitiveMgr.locate(primTypeID,
                                        srctype,
                                        CompositeType.SrcNoEa,
                                        SurfaceType.IntArgbPre);
    }

    public static synchronized TransformHelper getFromCache(SurfaceType src) {
        Object o = helpercache.get(src, null, null);
        if (o != null) {
            return (TransformHelper) o;
        }
        TransformHelper helper = locate(src);
        if (helper == null) {
            /*
            System.out.println("helper loop not found for:");
            System.out.println("src:  "+src);
            */
        } else {
            helpercache.put(src, null, null, helper);
        }
        return helper;
    }

    protected TransformHelper(SurfaceType srctype) {
        super(methodSignature, primTypeID, srctype,
              CompositeType.SrcNoEa,
              SurfaceType.IntArgbPre);
    }

    public TransformHelper(long pNativePrim, SurfaceType srctype,
                           CompositeType comptype,
                           SurfaceType dsttype)
    {
        super(pNativePrim, methodSignature, primTypeID,
              srctype, comptype, dsttype);
    }

    public native void Transform(MaskBlit output,
                                 SurfaceData src, SurfaceData dst,
                                 Composite comp, Region clip,
                                 AffineTransform itx, int txtype,
                                 int sx1, int sy1, int sx2, int sy2,
                                 int dx1, int dy1, int dx2, int dy2,
                                 int edges[], int dxoff, int dyoff);

    public GraphicsPrimitive makePrimitive(SurfaceType srctype,
                                           CompositeType comptype,
                                           SurfaceType dsttype)
    {
        return null;
    }

    public GraphicsPrimitive traceWrap() {
        return new TraceTransformHelper(this);
    }

    private static class TraceTransformHelper extends TransformHelper {
        TransformHelper target;

        public TraceTransformHelper(TransformHelper target) {
            super(target.getSourceType());
            this.target = target;
        }

        public GraphicsPrimitive traceWrap() {
            return this;
        }

        public void Transform(MaskBlit output,
                              SurfaceData src, SurfaceData dst,
                              Composite comp, Region clip,
                              AffineTransform itx, int txtype,
                              int sx1, int sy1, int sx2, int sy2,
                              int dx1, int dy1, int dx2, int dy2,
                              int edges[], int dxoff, int dyoff)
        {
            tracePrimitive(target);
            target.Transform(output, src, dst, comp, clip, itx, txtype,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             edges, dxoff, dyoff);
        }
    }
}
