/*
 * Copyright (c) 2013, 2015, Oracle and/or its affiliates. All rights reserved.
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

package sun.lwawt;

import java.awt.Rectangle;

public interface PlatformEventNotifier {
    void notifyIconify(boolean iconify);

    void notifyZoom(boolean isZoomed);

    void notifyExpose(Rectangle r);

    void notifyReshape(int x, int y, int w, int h);

    void notifyUpdateCursor();

    void notifyActivation(boolean activation, LWWindowPeer opposite);

    // MouseDown in non-client area
    void notifyNCMouseDown();

    /*
     * Called by the delegate to dispatch the event to Java. Event
     * coordinates are relative to non-client window are, i.e. the top-left
     * point of the client area is (insets.top, insets.left).
     */
    void notifyMouseEvent(int id, long when, int button,
                          int x, int y, int absX, int absY,
                          int modifiers, int clickCount, boolean popupTrigger,
                          byte[] bdata);

    void notifyMouseWheelEvent(long when, int x, int y, final int absX,
                               final int absY, int modifiers, int scrollType,
                               int scrollAmount, int wheelRotation,
                               double preciseWheelRotation, byte[] bdata);
    /*
     * Called by the delegate when a key is pressed.
     */
    void notifyKeyEvent(int id, long when, int modifiers,
                        int keyCode, char keyChar, int keyLocation);
}
