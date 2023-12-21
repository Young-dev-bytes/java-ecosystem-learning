/*
 * Copyright (c) 2008, 2019, Oracle and/or its affiliates. All rights reserved.
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

import java.nio.file.attribute.*;
import java.util.*;
import java.io.IOException;

/**
 * Bsd implementation of FileStore
 */

class BsdFileStore
    extends UnixFileStore
{
    BsdFileStore(UnixPath file) throws IOException {
        super(file);
    }

    BsdFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        super(fs, entry);
    }

    /**
     * Finds, and returns, the mount entry for the file system where the file
     * resides.
     */
    @Override
    UnixMountEntry findMountEntry() throws IOException {
        UnixFileSystem fs = file().getFileSystem();

        // step 1: get realpath
        UnixPath path = null;
        try {
            byte[] rp = UnixNativeDispatcher.realpath(file());
            path = new UnixPath(fs, rp);
        } catch (UnixException x) {
            x.rethrowAsIOException(file());
        }

        // step 2: find mount point
        byte[] dir = null;
        try {
            dir = BsdNativeDispatcher.getmntonname(path);
        } catch (UnixException x) {
            x.rethrowAsIOException(path);
        }

        // step 3: lookup mounted file systems
        for (UnixMountEntry entry: fs.getMountEntries()) {
            if (Arrays.equals(dir, entry.dir()))
                return entry;
        }

        throw new IOException("Mount point not found in fstab");
    }
}
