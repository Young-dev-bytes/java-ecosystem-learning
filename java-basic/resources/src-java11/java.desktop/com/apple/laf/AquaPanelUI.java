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

package com.apple.laf;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

import com.apple.laf.AquaUtils.RecyclableSingleton;
import com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;

import java.awt.Graphics;

public class AquaPanelUI extends BasicPanelUI {
    private static final RecyclableSingleton<AquaPanelUI> instance = new RecyclableSingletonFromDefaultConstructor<AquaPanelUI>(AquaPanelUI.class);

    public static ComponentUI createUI(final JComponent c) {
        return instance.get();
    }

    @Override
    public final void update(final Graphics g, final JComponent c) {
        if (c.isOpaque()) {
            AquaUtils.fillRect(g, c);
        }
        paint(g, c);
    }
}
