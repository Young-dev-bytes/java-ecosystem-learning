/*
 * Copyright (c) 2012, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyright (C) 1991-2016 Unicode, Inc. All rights reserved.
 * Distributed under the Terms of Use in 
 * http://www.unicode.org/copyright.html.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of the Unicode data files and any associated documentation
 * (the "Data Files") or Unicode software and any associated documentation
 * (the "Software") to deal in the Data Files or Software
 * without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, and/or sell copies of
 * the Data Files or Software, and to permit persons to whom the Data Files
 * or Software are furnished to do so, provided that
 * (a) this copyright and permission notice appear with all copies 
 * of the Data Files or Software,
 * (b) this copyright and permission notice appear in associated 
 * documentation, and
 * (c) there is clear notice in each modified Data File or in the Software
 * as well as in the documentation associated with the Data File(s) or
 * Software that the data or software has been modified.
 *
 * THE DATA FILES AND SOFTWARE ARE PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT OF THIRD PARTY RIGHTS.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS
 * NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL
 * DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THE DATA FILES OR SOFTWARE.
 *
 * Except as contained in this notice, the name of a copyright holder
 * shall not be used in advertising or otherwise to promote the sale,
 * use or other dealings in these Data Files or Software without prior
 * written authorization of the copyright holder.
 */

package sun.text.resources.cldr.ext;

import java.util.ListResourceBundle;

public class FormatData_ka extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthNames = new String[] {
               "\u10d8\u10d0\u10dc\u10d5\u10d0\u10e0\u10d8",
               "\u10d7\u10d4\u10d1\u10d4\u10e0\u10d5\u10d0\u10da\u10d8",
               "\u10db\u10d0\u10e0\u10e2\u10d8",
               "\u10d0\u10de\u10e0\u10d8\u10da\u10d8",
               "\u10db\u10d0\u10d8\u10e1\u10d8",
               "\u10d8\u10d5\u10dc\u10d8\u10e1\u10d8",
               "\u10d8\u10d5\u10da\u10d8\u10e1\u10d8",
               "\u10d0\u10d2\u10d5\u10d8\u10e1\u10e2\u10dd",
               "\u10e1\u10d4\u10e5\u10e2\u10d4\u10db\u10d1\u10d4\u10e0\u10d8",
               "\u10dd\u10e5\u10e2\u10dd\u10db\u10d1\u10d4\u10e0\u10d8",
               "\u10dc\u10dd\u10d4\u10db\u10d1\u10d4\u10e0\u10d8",
               "\u10d3\u10d4\u10d9\u10d4\u10db\u10d1\u10d4\u10e0\u10d8",
               "",
            };
        final String[] metaValue_MonthAbbreviations = new String[] {
               "\u10d8\u10d0\u10dc",
               "\u10d7\u10d4\u10d1",
               "\u10db\u10d0\u10e0",
               "\u10d0\u10de\u10e0",
               "\u10db\u10d0\u10d8",
               "\u10d8\u10d5\u10dc",
               "\u10d8\u10d5\u10da",
               "\u10d0\u10d2\u10d5",
               "\u10e1\u10d4\u10e5",
               "\u10dd\u10e5\u10e2",
               "\u10dc\u10dd\u10d4",
               "\u10d3\u10d4\u10d9",
               "",
            };
        final String[] metaValue_MonthNarrows = new String[] {
               "\u10d8",
               "\u10d7",
               "\u10db",
               "\u10d0",
               "\u10db",
               "\u10d8",
               "\u10d8",
               "\u10d0",
               "\u10e1",
               "\u10dd",
               "\u10dc",
               "\u10d3",
               "",
            };
        final String[] metaValue_DayNames = new String[] {
               "\u10d9\u10d5\u10d8\u10e0\u10d0",
               "\u10dd\u10e0\u10e8\u10d0\u10d1\u10d0\u10d7\u10d8",
               "\u10e1\u10d0\u10db\u10e8\u10d0\u10d1\u10d0\u10d7\u10d8",
               "\u10dd\u10d7\u10ee\u10e8\u10d0\u10d1\u10d0\u10d7\u10d8",
               "\u10ee\u10e3\u10d7\u10e8\u10d0\u10d1\u10d0\u10d7\u10d8",
               "\u10de\u10d0\u10e0\u10d0\u10e1\u10d9\u10d4\u10d5\u10d8",
               "\u10e8\u10d0\u10d1\u10d0\u10d7\u10d8",
            };
        final String[] metaValue_DayAbbreviations = new String[] {
               "\u10d9\u10d5\u10d8",
               "\u10dd\u10e0\u10e8",
               "\u10e1\u10d0\u10db",
               "\u10dd\u10d7\u10ee",
               "\u10ee\u10e3\u10d7",
               "\u10de\u10d0\u10e0",
               "\u10e8\u10d0\u10d1",
            };
        final String[] metaValue_DayNarrows = new String[] {
               "\u10d9",
               "\u10dd",
               "\u10e1",
               "\u10dd",
               "\u10ee",
               "\u10de",
               "\u10e8",
            };
        final String[] metaValue_QuarterNames = new String[] {
               "I \u10d9\u10d5\u10d0\u10e0\u10e2\u10d0\u10da\u10d8",
               "II \u10d9\u10d5\u10d0\u10e0\u10e2\u10d0\u10da\u10d8",
               "III \u10d9\u10d5\u10d0\u10e0\u10e2\u10d0\u10da\u10d8",
               "IV \u10d9\u10d5\u10d0\u10e0\u10e2\u10d0\u10da\u10d8",
            };
        final String[] metaValue_QuarterAbbreviations = new String[] {
               "I \u10d9\u10d5.",
               "II \u10d9\u10d5.",
               "III \u10d9\u10d5.",
               "IV \u10d9\u10d5.",
            };
        final String[] metaValue_narrow_AmPmMarkers = new String[] {
               "a",
               "p",
            };
        final String[] metaValue_Eras = new String[] {
               "\u10eb\u10d5. \u10ec.",
               "\u10d0\u10ee. \u10ec.",
            };
        final String[] metaValue_TimePatterns = new String[] {
               "HH:mm:ss zzzz",
               "HH:mm:ss z",
               "HH:mm:ss",
               "HH:mm",
            };
        final String[] metaValue_buddhist_QuarterNarrows = new String[] {
               "1",
               "2",
               "3",
               "4",
            };
        final String[] metaValue_java_time_buddhist_DatePatterns = new String[] {
               "EEEE, dd MMMM, y G",
               "d MMMM, y G",
               "d MMM, y G",
               "dd.MM.y GGGGG",
            };
        final String[] metaValue_buddhist_DatePatterns = new String[] {
               "EEEE, dd MMMM, y GGGG",
               "d MMMM, y GGGG",
               "d MMM, y GGGG",
               "dd.MM.y G",
            };
        final String[] metaValue_roc_AmPmMarkers = new String[] {
               "AM",
               "PM",
            };
        final String metaValue_calendarname_gregorian = "\u10d2\u10e0\u10d8\u10d2\u10dd\u10e0\u10d8\u10d0\u10dc\u10e3\u10da\u10d8 \u10d9\u10d0\u10da\u10d4\u10dc\u10d3\u10d0\u10e0\u10d8";
        final Object[][] data = new Object[][] {
            { "MonthNames", metaValue_MonthNames },
            { "field.year", "\u10ec\u10d4\u10da\u10d8" },
            { "islamic.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "roc.DayAbbreviations", metaValue_DayAbbreviations },
            { "standalone.DayNarrows", metaValue_DayNarrows },
            { "islamic.AmPmMarkers", metaValue_roc_AmPmMarkers },
            { "java.time.japanese.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "standalone.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "roc.QuarterNames", metaValue_QuarterNames },
            { "TimePatterns", metaValue_TimePatterns },
            { "islamic.DayNarrows", metaValue_DayNarrows },
            { "field.zone", "\u10d3\u10e0\u10dd\u10d8\u10e1 \u10e1\u10d0\u10e0\u10e2\u10e7\u10d4\u10da\u10d8" },
            { "roc.MonthNarrows", metaValue_MonthNarrows },
            { "calendarname.islamic-civil", "\u10d8\u10e1\u10da\u10d0\u10db\u10e3\u10e0\u10d8 \u10e1\u10d0\u10db\u10dd\u10e5\u10d0\u10da\u10d0\u10e5\u10dd \u10d9\u10d0\u10da\u10d4\u10dc\u10d3\u10d0\u10e0\u10d8" },
            { "japanese.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "islamic.narrow.AmPmMarkers", metaValue_narrow_AmPmMarkers },
            { "japanese.TimePatterns", metaValue_TimePatterns },
            { "narrow.Eras", metaValue_Eras },
            { "timezone.regionFormat.standard", "{0} \u10e1\u10e2\u10d0\u10dc\u10d3\u10d0\u10e0\u10e2\u10e3\u10da\u10d8 \u10d3\u10e0\u10dd" },
            { "calendarname.japanese", "\u10d8\u10d0\u10de\u10dd\u10dc\u10e3\u10e0\u10d8 \u10d9\u10d0\u10da\u10d4\u10dc\u10d3\u10d0\u10e0\u10d8" },
            { "buddhist.narrow.AmPmMarkers", metaValue_narrow_AmPmMarkers },
            { "Eras", metaValue_Eras },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "roc.DayNames", metaValue_DayNames },
            { "standalone.DayAbbreviations", metaValue_DayAbbreviations },
            { "roc.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "islamic.QuarterNames", metaValue_QuarterNames },
            { "long.Eras",
                new String[] {
                    "\u10eb\u10d5\u10d4\u10da\u10d8 \u10ec\u10d4\u10da\u10d7\u10d0\u10e6\u10e0\u10d8\u10ea\u10ee\u10d5\u10d8\u10d7",
                    "\u10d0\u10ee\u10d0\u10da\u10d8 \u10ec\u10d4\u10da\u10d7\u10d0\u10e6\u10e0\u10d8\u10ea\u10ee\u10d5\u10d8\u10d7",
                }
            },
            { "islamic.DayNames", metaValue_DayNames },
            { "java.time.islamic.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "buddhist.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "field.weekday", "\u10d9\u10d5\u10d8\u10e0\u10d8\u10e1 \u10d3\u10e6\u10d4" },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "DateTimePatterns",
                new String[] {
                    "{1}, {0}",
                    "{1}, {0}",
                    "{1}, {0}",
                    "{1}, {0}",
                }
            },
            { "narrow.AmPmMarkers", metaValue_narrow_AmPmMarkers },
            { "latn.NumberElements",
                new String[] {
                    ",",
                    "\u00a0",
                    ";",
                    "%",
                    "0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "\u10d0\u10e0\u00a0\u10d0\u10e0\u10d8\u10e1\u00a0\u10e0\u10d8\u10ea\u10ee\u10d5\u10d8",
                }
            },
            { "MonthNarrows", metaValue_MonthNarrows },
            { "japanese.DatePatterns", metaValue_buddhist_DatePatterns },
            { "japanese.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "buddhist.DayNames", metaValue_DayNames },
            { "field.minute", "\u10ec\u10e3\u10d7\u10d8" },
            { "field.era", "\u10d4\u10de\u10dd\u10e5\u10d0" },
            { "islamic.DayAbbreviations", metaValue_DayAbbreviations },
            { "field.dayperiod", "\u10d3\u10e6\u10d8\u10e1 \u10dc\u10d0\u10ee\u10d4\u10d5\u10d0\u10e0\u10d8" },
            { "standalone.MonthNarrows", metaValue_MonthNarrows },
            { "japanese.QuarterNames", metaValue_QuarterNames },
            { "buddhist.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "calendarname.roc", "\u10e9\u10d8\u10dc\u10d4\u10d7\u10d8\u10e1 \u10e0\u10d4\u10e1\u10de\u10e3\u10d1\u10da\u10d8\u10d9\u10d8\u10e1 \u10d9\u10d0\u10da\u10d4\u10dc\u10d3\u10d0\u10e0\u10d8" },
            { "islamic.DatePatterns", metaValue_buddhist_DatePatterns },
            { "roc.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "japanese.DayNames", metaValue_DayNames },
            { "japanese.DayAbbreviations", metaValue_DayAbbreviations },
            { "DayNames", metaValue_DayNames },
            { "field.month", "\u10d7\u10d5\u10d4" },
            { "buddhist.DatePatterns", metaValue_buddhist_DatePatterns },
            { "field.second", "\u10ec\u10d0\u10db\u10d8" },
            { "roc.MonthNames", metaValue_MonthNames },
            { "field.week", "\u10d9\u10d5\u10d8\u10e0\u10d0" },
            { "DayAbbreviations", metaValue_DayAbbreviations },
            { "DayNarrows", metaValue_DayNarrows },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "#,##0.00\u00a0\u00a4",
                    "#,##0%",
                }
            },
            { "roc.DatePatterns", metaValue_buddhist_DatePatterns },
            { "buddhist.MonthNarrows", metaValue_MonthNarrows },
            { "buddhist.QuarterNames", metaValue_QuarterNames },
            { "islamic.QuarterNarrows", metaValue_buddhist_QuarterNarrows },
            { "calendarname.islamic", "\u10d8\u10e1\u10da\u10d0\u10db\u10e3\u10e0\u10d8 \u10d9\u10d0\u10da\u10d4\u10dc\u10d3\u10d0\u10e0\u10d8" },
            { "roc.DayNarrows", metaValue_DayNarrows },
            { "roc.AmPmMarkers", metaValue_roc_AmPmMarkers },
            { "java.time.roc.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "java.time.buddhist.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "calendarname.gregorian", metaValue_calendarname_gregorian },
            { "timezone.regionFormat.daylight", "{0} \u10d6\u10d0\u10e4\u10ee\u10e3\u10da\u10d8\u10e1 \u10d3\u10e0\u10dd" },
            { "DatePatterns",
                new String[] {
                    "EEEE, dd MMMM, y",
                    "d MMMM, y",
                    "d MMM. y",
                    "dd.MM.yy",
                }
            },
            { "buddhist.DayAbbreviations", metaValue_DayAbbreviations },
            { "islamic.TimePatterns", metaValue_TimePatterns },
            { "MonthAbbreviations", metaValue_MonthAbbreviations },
            { "standalone.DayNames", metaValue_DayNames },
            { "field.hour", "\u10e1\u10d0\u10d0\u10d7\u10d8" },
            { "japanese.narrow.AmPmMarkers", metaValue_narrow_AmPmMarkers },
            { "buddhist.TimePatterns", metaValue_TimePatterns },
            { "calendarname.buddhist", "\u10d1\u10e3\u10d3\u10d8\u10e1\u10e2\u10e3\u10e0\u10d8 \u10d9\u10d0\u10da\u10d4\u10dc\u10d3\u10d0\u10e0\u10d8" },
            { "standalone.MonthNames", metaValue_MonthNames },
            { "standalone.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "timezone.regionFormat", "\u10d3\u10e0\u10dd: {0}" },
            { "buddhist.DayNarrows", metaValue_DayNarrows },
            { "roc.narrow.AmPmMarkers", metaValue_narrow_AmPmMarkers },
            { "buddhist.QuarterNarrows", metaValue_buddhist_QuarterNarrows },
            { "japanese.DayNarrows", metaValue_DayNarrows },
            { "QuarterNames", metaValue_QuarterNames },
            { "roc.TimePatterns", metaValue_TimePatterns },
            { "QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "standalone.QuarterNames", metaValue_QuarterNames },
            { "japanese.MonthNarrows", metaValue_MonthNarrows },
            { "calendarname.gregory", metaValue_calendarname_gregorian },
        };
        return data;
    }
}
