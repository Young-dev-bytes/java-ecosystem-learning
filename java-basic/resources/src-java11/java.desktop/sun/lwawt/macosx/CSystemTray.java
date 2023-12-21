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

import java.awt.Dimension;
import java.awt.peer.SystemTrayPeer;

public class CSystemTray implements SystemTrayPeer {

    CSystemTray(){
    }

    @Override
    public Dimension getTrayIconSize() {
        // TODO: ask the native system.
        return new Dimension(20, 20);
    }
}
