/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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

public interface SecurityWarningWindow extends PlatformWindow {
    /**
     * @param x,y,w,h coordinates of the untrusted window
     */
    public void reposition(int x, int y, int w, int h);

    public void setVisible(boolean visible, boolean doSchedule);
}
