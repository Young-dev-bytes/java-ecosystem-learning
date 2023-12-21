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

package sun.lwawt.macosx;

import sun.lwawt.LWCursorManager;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.geom.Point2D;

final class CCursorManager extends LWCursorManager {

    private static native Point2D nativeGetCursorPosition();
    private static native void nativeSetBuiltInCursor(final int type, final String name);
    private static native void nativeSetCustomCursor(final long imgPtr, final double x, final double y);
    public static native void nativeSetAllowsCursorSetInBackground(final boolean allows);

    private static final int NAMED_CURSOR = -1;

    private static final CCursorManager theInstance = new CCursorManager();
    public static CCursorManager getInstance() {
        return theInstance;
    }

    private volatile Cursor currentCursor;

    private CCursorManager() { }

    @Override
    protected Point getCursorPosition() {
        final Point2D nativePosition = nativeGetCursorPosition();
        return new Point((int)nativePosition.getX(), (int)nativePosition.getY());
    }

    @Override
    protected void setCursor(final Cursor cursor) {
        if (cursor == currentCursor) {
            return;
        }
        currentCursor = cursor;

        if (cursor == null) {
            nativeSetBuiltInCursor(Cursor.DEFAULT_CURSOR, null);
            return;
        }

        if (cursor instanceof CCustomCursor) {
            final CCustomCursor customCursor = (CCustomCursor) cursor;
            final long imagePtr = customCursor.getImageData();
            if (imagePtr != 0L) {
                final Point hotSpot = customCursor.getHotSpot();
                nativeSetCustomCursor(imagePtr, hotSpot.x, hotSpot.y);
            }
            return;
        }

        final int type = cursor.getType();
        if (type != Cursor.CUSTOM_CURSOR) {
            nativeSetBuiltInCursor(type, null);
            return;
        }

        final String name = cursor.getName();
        if (name != null) {
            nativeSetBuiltInCursor(NAMED_CURSOR, name);
            return;
        }

        // do something special
        throw new RuntimeException("Unimplemented");
    }
}
