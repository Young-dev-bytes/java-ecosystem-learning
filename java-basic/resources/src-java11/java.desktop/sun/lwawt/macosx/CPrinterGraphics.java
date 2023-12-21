/*
 * Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.*;
import java.awt.image.*;
import java.awt.print.*;
import sun.print.*;

public class CPrinterGraphics extends ProxyGraphics2D {
    // NOTE: This is a ProxyGraphics2D, and not a PathGraphics. However
    // the RasterPrinterJob, upon which CPrinterJob is based, refers to
    // PathGraphics. However, this is not a code path that will be
    // encountered by CPrinterJob/CPrinterGraphics. This is because
    // CPrinterGraphics wraps a SunGraphics2D that has a OSXSurfaceData
    // based CPrinterSurfaceData. It can do "path graphics" because it
    // is based upon CoreGraphics. See WPathGraphics and PSPathGraphics.

    public CPrinterGraphics(Graphics2D graphics, PrinterJob printerJob) {
        super(graphics, printerJob);
    }

    public boolean drawImage(Image img, int x, int y,
                 Color bgcolor,
                 ImageObserver observer) {
        // ProxyGraphics2D works around a problem that shouldn't be
        // a problem with CPrinterSurfaceData (and the decision method,
        // needToCopyBgColorImage, is private instead of protected!)
        return getDelegate().drawImage(img, x, y, bgcolor, observer);
    }

    public boolean drawImage(Image img, int x, int y,
                 int width, int height,
                 Color bgcolor,
                 ImageObserver observer) {
        // ProxyGraphics2D works around a problem that shouldn't be
        // a problem with CPrinterSurfaceData (and the decision method,
        // needToCopyBgColorImage, is private instead of protected!)
        return getDelegate().drawImage(img, x, y, width, height, bgcolor, observer);
    }

    public boolean drawImage(Image img,
                 int dx1, int dy1, int dx2, int dy2,
                 int sx1, int sy1, int sx2, int sy2,
                 Color bgcolor,
                 ImageObserver observer) {
        // ProxyGraphics2D works around a problem that shouldn't be
        // a problem with CPrinterSurfaceData (and the decision method,
        // needToCopyBgColorImage, is private instead of protected!)
        return getDelegate().drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
    }
}
