/*
 * Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved.
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

package java.util.prefs;

class MacOSXPreferencesFactory implements PreferencesFactory {
    @Override
    public Preferences userRoot() {
        return MacOSXPreferences.getUserRoot();
    }

    @Override
    public Preferences systemRoot() {
        return MacOSXPreferences.getSystemRoot();
    }
}
