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


package sun.lwawt.macosx;

import sun.lwawt.PlatformWindow;

class CPlatformLWComponent extends CPlatformComponent {

    CPlatformLWComponent() {
        super();
    }

    @Override
    public long getPointer() {
        return 0;
    }

    @Override
    public void initialize(final PlatformWindow platformWindow) {
    }

    @Override
    public void setBounds(final int x, final int y, final int w, final int h) {
    }

    @Override
    public void dispose() {
    }
}
