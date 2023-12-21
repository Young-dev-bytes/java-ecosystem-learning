/*
 * Copyright (c) 2014, 2018, Oracle and/or its affiliates. All rights reserved.
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
 * Defines the API for JDK Flight Recorder.
 * <p>
 *
 * <dl style="font-family:'DejaVu Sans', Arial, Helvetica, sans serif">
 * <dt class="simpleTagLabel">Tool Guides:
 * <dd>{@extLink jfr_tool_reference jfr}
 * </dl>
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jfr {
    exports jdk.jfr;
    exports jdk.jfr.consumer;

    exports jdk.jfr.internal.management to jdk.management.jfr;
}
