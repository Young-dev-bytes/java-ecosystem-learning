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


package sun.lwawt;

/**
 * Can be used to store information about native resource related to the
 * lightweight component.
 */
public interface PlatformComponent {

    /**
     * Initializes platform component.
     *
     * @param platformWindow already initialized {@code PlatformWindow}.
     */
    void initialize(PlatformWindow platformWindow);

    /**
     * Moves and resizes this component. The new location of the top-left corner
     * is specified by {@code x} and {@code y}, and the new size is specified by
     * {@code w} and {@code h}. The location is specified relative to the {@code
     * platformWindow}.
     *
     * @param x the X location of the component
     * @param y the Y location of the component
     * @param w the width of the component
     * @param h the height of the component
     */
    void setBounds(int x, int y, int w, int h);

    /**
     * Releases all of the native resources used by this {@code
     * PlatformComponent}.
     */
    void dispose();
}
