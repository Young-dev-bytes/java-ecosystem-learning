/*
 * Copyright (c) 2017, 2018, Oracle and/or its affiliates. All rights reserved.
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

package sun.nio.fs;

import java.nio.file.FileSystem;

/**
 * Creates this platform's default FileSystemProvider.
 */

public class DefaultFileSystemProvider {
    private static final MacOSXFileSystemProvider INSTANCE
        = new MacOSXFileSystemProvider();

    private DefaultFileSystemProvider() { }

    /**
     * Returns the platform's default file system provider.
     */
    public static MacOSXFileSystemProvider instance() {
        return INSTANCE;
    }

    /**
     * Returns the platform's default file system.
     */
    public static FileSystem theFileSystem() {
        return INSTANCE.theFileSystem();
    }
}
