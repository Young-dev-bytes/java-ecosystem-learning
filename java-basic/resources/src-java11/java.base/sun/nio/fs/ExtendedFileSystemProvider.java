/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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

import java.nio.file.Path;

/**
 * Fast path methods for FileSystemProvider implementations
 */
public interface ExtendedFileSystemProvider {

    /**
     * Checks the existence of a file.
     *
     * @return  {@code true} if the file exists; {@code false} if the file does
     *          not exist or its existence cannot be determined.
     */
    boolean exists(Path file);

    /**
     * Tests whether a file is a directory.
     *
     * @return  {@code true} if the file is a directory; {@code false} if
     *          the file does not exist, is not a directory, or it cannot
     *          be determined if the file is a directory or not.
     */
    boolean isDirectory(Path file);

    /**
     * Tests whether a file is a regular file with opaque content.
     *
     * @return  {@code true} if the file is a regular file; {@code false} if
     *          the file does not exist, is not a regular file, or it
     *          cannot be determined if the file is a regular file or not.
     */
    boolean isRegularFile(Path file);
}
