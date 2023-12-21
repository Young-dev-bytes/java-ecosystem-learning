/*
 * Copyright (c) 2010, 2016, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.formats.html.markup;

import jdk.javadoc.internal.doclets.toolkit.util.Utils;

/**
 * Enum representing HTML tag attributes.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Bhavesh Patel
 */
public enum HtmlAttr {
    ALT,
    CLASS,
    CLEAR,
    COLS,
    CONTENT,
    DISABLED,
    FOR,
    HREF,
    HTTP_EQUIV("http-equiv"),
    ID,
    LANG,
    NAME,
    ONCLICK,
    ONLOAD,
    REL,
    ROLE,
    ROWS,
    SCOPE,
    SCROLLING,
    SRC,
    SUMMARY,
    TARGET,
    TITLE,
    TYPE,
    VALUE,
    WIDTH;

    private final String value;

    public enum Role {

        BANNER,
        CONTENTINFO,
        MAIN,
        NAVIGATION,
        REGION;

        private final String role;

        Role() {
            role = Utils.toLowerCase(name());
        }

        public String toString() {
            return role;
        }
    }

    HtmlAttr() {
        this.value = Utils.toLowerCase(name());
    }

    HtmlAttr(String name) {
        this.value = name;
    }

    public String toString() {
        return value;
    }
}
