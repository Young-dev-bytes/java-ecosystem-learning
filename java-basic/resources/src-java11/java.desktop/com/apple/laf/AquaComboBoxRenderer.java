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

package com.apple.laf;

import javax.swing.*;
import javax.swing.plaf.UIResource;

@SuppressWarnings("serial") // Superclass is not serializable across versions
class AquaComboBoxRenderer extends AquaComboBoxRendererInternal<Object> implements UIResource {
    public AquaComboBoxRenderer(final JComboBox<?> comboBox) {
        super(comboBox);
    }
}
