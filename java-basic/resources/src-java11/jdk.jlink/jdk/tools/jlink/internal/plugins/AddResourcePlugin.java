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
 * Base plugin to add a resource
 */
abstract class AddResourcePlugin implements Plugin {

    private final String name;
    private final String path;
    private String value;

    protected AddResourcePlugin(String n, String p) {
        name = n;
        path = p;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return PluginsResourceBundle.getDescription(name);
    }

    @Override
    public Category getType() {
        return Category.ADDER;
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public boolean hasRawArgument() {
        return true;
    }

    @Override
    public String getArgumentsDescription() {
       return PluginsResourceBundle.getArgument(name);
    }

    @Override
    public void configure(Map<String, String> config) {
        var v = config.get(name);
        if (v == null)
            throw new AssertionError();
        value = v;
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        in.transformAndCopy(Function.identity(), out);
        out.add(ResourcePoolEntry.create(path,
                                         value.getBytes(StandardCharsets.UTF_8)));
        return out.build();
    }

}
