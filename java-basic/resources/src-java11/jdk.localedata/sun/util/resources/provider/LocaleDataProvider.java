/*
 * Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
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

package sun.util.resources.provider;

import java.util.Locale;
import java.util.ResourceBundle;
import sun.util.resources.LocaleData;

/**
 * Service Provider for loading locale data resource bundles in jdk.localedata
 * except for JavaTimeSupplementary resource bundles.
 */
public class LocaleDataProvider extends LocaleData.CommonResourceBundleProvider {
    @Override
    public ResourceBundle getBundle(String baseName, Locale locale) {
        return loadResourceBundle(toBundleName(baseName, locale));
    }

    /**
     * Utility method for loading a resource bundle in jdk.localedata.
     */
    static ResourceBundle loadResourceBundle(String bundleName) {
        Class<?> c = Class.forName(LocaleDataProvider.class.getModule(), bundleName);
        if (c != null && ResourceBundle.class.isAssignableFrom(c)) {
            try {
                @SuppressWarnings({"unchecked", "deprecation"})
                ResourceBundle rb = ((Class<ResourceBundle>) c).newInstance();
                return rb;
            } catch (InstantiationException | IllegalAccessException e) {
                throw new InternalError(e);
            }
        }
        return null;
    }
}
