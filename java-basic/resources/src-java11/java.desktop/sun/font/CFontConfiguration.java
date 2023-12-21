/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
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

package sun.font;

import java.nio.charset.Charset;
import java.util.HashMap;
import sun.awt.FontConfiguration;
import sun.font.CompositeFontDescriptor;
import sun.font.SunFontManager;

class CFontConfiguration extends FontConfiguration {

    private static CompositeFontDescriptor[] emptyDescriptors =
        new CompositeFontDescriptor[0];
    private static String[] emptyStrings = new String[0];

    public CFontConfiguration(SunFontManager fm) {
        super(fm);
    }

    public CFontConfiguration(SunFontManager fm,
                              boolean preferLocaleFonts,
                              boolean preferPropFonts)
    {
        super(fm, preferLocaleFonts, preferPropFonts);
    }

    /*
     * On Mac OS X we essentially ignore the font.properties file, and do
     * it all programatically.  The intention is end users will use things
     * like the Font Book to manage fonts. Plus our fonts automatically do
     * unicode substitution, so a localized font is not required.
     *
     * The following methods therefore act like stubs and return empty values.
     */

    @Override
    public int getNumberCoreFonts() {
        return 0;
    }

    @Override
    public String[] getPlatformFontNames() {
        return emptyStrings;
    }

    @Override
    public CompositeFontDescriptor[] get2DCompositeFontInfo() {
        return emptyDescriptors;
    }

    @Override
    protected String mapFileName(String fileName) {
        return "";
    }

    @Override
    protected Charset getDefaultFontCharset(String fontName) {
        return Charset.forName("ISO8859_1");
    }

    @Override
    protected String getEncoding(String awtFontName, String charSubsetName) {
        return "default";
    }

    @Override
    protected String getFaceNameFromComponentFontName(String compFontName) {
        return compFontName;
    }

    @Override
    protected String getFileNameFromComponentFontName(String compFontName) {
        return compFontName;
    }

    @Override
    public String getFallbackFamilyName(String fontName,
                                        String defaultFallback)
    {
        return defaultFallback;
    }

    @Override
    protected void initReorderMap() {
        reorderMap = new HashMap<>();
    }
}
