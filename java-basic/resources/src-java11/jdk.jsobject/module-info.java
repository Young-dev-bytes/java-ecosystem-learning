/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
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
 * Defines the API for the JavaScript Object.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.jsobject {
    requires java.desktop;

    exports netscape.javascript;

    uses jdk.internal.netscape.javascript.spi.JSObjectProvider;
}
