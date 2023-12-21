/*
 * Copyright (c) 2011, 2016, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Font;
import java.awt.MenuComponent;
import java.awt.peer.MenuComponentPeer;

abstract class CMenuComponent extends CFRetainedResource
        implements MenuComponentPeer {

    private final MenuComponent target;

    CMenuComponent(final MenuComponent target) {
        super(0, true);
        this.target = target;
        setPtr(createModel());
    }

    final MenuComponent getTarget() {
        return target;
    }

    abstract long createModel();

    @Override
    public final void dispose() {
        super.dispose();
        LWCToolkit.targetDisposedPeer(target, this);
    }

    // 1.5 peer method
    @Override
    public final void setFont(final Font f) {
        // no-op, as we don't currently support menu fonts
        // c.f. radar 4032912
    }
}
