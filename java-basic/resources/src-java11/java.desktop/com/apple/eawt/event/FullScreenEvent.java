/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
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

package com.apple.eawt.event;

import com.apple.eawt.Application;
import java.awt.Window;
import java.util.EventObject;

@SuppressWarnings("serial") // JDK implementation class
public class FullScreenEvent extends EventObject {

        final Window window;

        /**
         * @param window window
         */
        public FullScreenEvent(final Window window) {
            super(Application.getApplication());
            this.window = window;
        }

        /**
         * @return window transitioning between full screen states
         */
        public Window getWindow() {
            return window;
        }
}
