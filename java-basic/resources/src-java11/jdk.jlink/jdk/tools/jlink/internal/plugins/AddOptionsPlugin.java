/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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

package jdk.tools.jlink.internal.plugins;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import jdk.tools.jlink.plugin.*;

/**
 * Plugin to add VM command-line options
 */
public final class AddOptionsPlugin extends AddResourcePlugin {

    public AddOptionsPlugin() {
        super("add-options", "/java.base/jdk/internal/vm/options");
    }

}
