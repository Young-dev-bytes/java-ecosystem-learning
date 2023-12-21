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

package sun.awt;

/**
 * A class implements the FullScreenCapable interface to
 * indicate that it's capable to enter the full-screen mode.
 */
public interface FullScreenCapable {

    /**
     * Enters full-screen mode.
     */
    public void enterFullScreenMode();

    /**
     * Returns to windowed mode.
     */
    public void exitFullScreenMode();

}
