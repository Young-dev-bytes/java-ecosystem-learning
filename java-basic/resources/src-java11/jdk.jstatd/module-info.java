/*
 * Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
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
 * Defines the <em>{@index jstatd jstatd tool}</em> tool for starting a daemon
 * for the jstat tool to monitor JVM statistics remotely.
 *
 * <dl style="font-family:'DejaVu Sans', Arial, Helvetica, sans serif">
 * <dt class="simpleTagLabel">Tool Guides:
 * <dd>{@extLink jstatd_tool_reference jstatd}
 * </dl>
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jstatd {
    requires java.rmi;
    requires jdk.internal.jvmstat;

    // RMI needs to serialize types in this package
    exports sun.jvmstat.monitor.remote to java.rmi;

    provides sun.jvmstat.monitor.MonitoredHostService with
        sun.jvmstat.perfdata.monitor.protocol.rmi.MonitoredHostRmiService;
}
