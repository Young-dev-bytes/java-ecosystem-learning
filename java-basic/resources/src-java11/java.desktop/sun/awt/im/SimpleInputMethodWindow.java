/*
 * Copyright (c) 1999, 2011, Oracle and/or its affiliates. All rights reserved.
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

package sun.awt.im;

import java.awt.Frame;

/**
 * Implements a simple input method window that provides the minimal
 * functionality as specified in
 * {@link java.awt.im.spi.InputMethodContext#createInputMethodWindow}.
 *
 */
public class SimpleInputMethodWindow
        extends Frame
        implements InputMethodWindow {

    InputContext inputContext = null;

    /**
     * Constructs a simple input method window.
     */
    public SimpleInputMethodWindow(String title, InputContext context) {
        super(title);
        if (context != null) {
            this.inputContext = context;
        }
        setFocusableWindowState(false);
    }

    public void setInputContext(InputContext inputContext) {
        this.inputContext = inputContext;
    }

    public java.awt.im.InputContext getInputContext() {
        if (inputContext != null) {
            return inputContext;
        } else {
            return super.getInputContext();
        }
    }

    // Proclaim serial compatibility with 1.7.0
    private static final long serialVersionUID = 5093376647036461555L;
}
