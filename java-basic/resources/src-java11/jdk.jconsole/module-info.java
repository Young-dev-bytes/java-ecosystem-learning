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
 * Defines the JMX graphical tool, <em>{@index jconsole jconsole}</em>,
 * for monitoring and managing a running application.
 *
 * <dl style="font-family:'DejaVu Sans', Arial, Helvetica, sans serif">
 * <dt class="simpleTagLabel">Tool Guides:
 * <dd>{@extLink jconsole_tool_reference jconsole},
 *     {@extLink using_jconsole Using JConsole}
 * </dl>
 *
 * @uses com.sun.tools.jconsole.JConsolePlugin
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jconsole {
    requires java.management.rmi;
    requires java.rmi;
    requires jdk.attach;
    requires jdk.internal.jvmstat;
    requires jdk.management;
    requires jdk.management.agent;

    requires transitive java.desktop;
    requires transitive java.management;

    exports com.sun.tools.jconsole;

    uses com.sun.tools.jconsole.JConsolePlugin;
}
