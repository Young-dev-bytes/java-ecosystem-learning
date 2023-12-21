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

package com.apple.eawt;

import java.awt.*;
import java.beans.SimpleBeanInfo;

/**
 * This class is used by JavaBeans tools and should not be used directly by applications.
 */
public class ApplicationBeanInfo extends SimpleBeanInfo {
    public Image getIcon(final int iconKind) {
        return Toolkit.getDefaultToolkit().getImage("NSImage://NSGenericApplication");
    }
}
