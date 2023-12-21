/*
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Defines the implementation of the HotSpot Serviceability Agent.
 *
 * <p> This module includes the <em>{@index jhsdb jhsdb tool}</em> tool to
 * attach to a running Java Virtual Machine (JVM) or launch a postmortem
 * debugger to analyze the content of a core-dump from a crashed JVM.
 *
 * <dl style="font-family:'DejaVu Sans', Arial, Helvetica, sans serif">
 * <dt class="simpleTagLabel">Tool Guides:</dt>
 * <dd> {@extLink jhsdb_tool_reference jhsdb}</dd>
 * </dl>
 *
 * @moduleGraph
 * @since 9
 */
module jdk.hotspot.agent {
    requires java.datatransfer;
    requires java.desktop;
    requires java.rmi;
    requires java.scripting;

    // RMI needs to serialize types in this package
    exports sun.jvm.hotspot.debugger.remote to java.rmi;

}
