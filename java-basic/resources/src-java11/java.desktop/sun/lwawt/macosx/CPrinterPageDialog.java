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

package sun.lwawt.macosx;


import java.awt.*;
import java.awt.print.*;

@SuppressWarnings("serial") // JDK implementation class
final class CPrinterPageDialog extends CPrinterDialog {
    private PageFormat fPage;
    private Printable fPainter;

    CPrinterPageDialog(Frame parent, CPrinterJob printerJob, PageFormat page, Printable painter) {
        super(parent, printerJob);
        fPage = page;
        fPainter = painter;
    }

    @Override
    protected native boolean showDialog();
}
