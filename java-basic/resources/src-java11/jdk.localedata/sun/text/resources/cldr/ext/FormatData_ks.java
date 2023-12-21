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

public class FormatData_ks extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthNames = new String[] {
               "\u062c\u0646\u0624\u0631\u06cc",
               "\u0641\u0631\u0624\u0631\u06cc",
               "\u0645\u0627\u0631\u0655\u0686",
               "\u0627\u067e\u0631\u06cc\u0644",
               "\u0645\u06cc\u0654",
               "\u062c\u0648\u0657\u0646",
               "\u062c\u0648\u0657\u0644\u0627\u06cc\u06cc",
               "\u0627\u06af\u0633\u062a",
               "\u0633\u062a\u0645\u0628\u0631",
               "\u0627\u06a9\u062a\u0648\u0657\u0628\u0631",
               "\u0646\u0648\u0645\u0628\u0631",
               "\u062f\u0633\u0645\u0628\u0631",
               "",
            };
        final String[] metaValue_MonthNarrows = new String[] {
               "\u062c",
               "\u0641",
               "\u0645",
               "\u0627",
               "\u0645",
               "\u062c",
               "\u062c",
               "\u0627",
               "\u0633",
               "\u0633",
               "\u0627",
               "\u0646",
               "",
            };
        final String[] metaValue_DayNames = new String[] {
               "\u0627\u064e\u062a\u06be\u0648\u0627\u0631",
               "\u0698\u0654\u0646\u065b\u062f\u0631\u0655\u0631\u0648\u0627\u0631",
               "\u0628\u0648\u065a\u0645\u0648\u0627\u0631",
               "\u0628\u0648\u062f\u0648\u0627\u0631",
               "\u0628\u0631\u065b\u066e\u06ea\u0633\u0648\u0627\u0631",
               "\u062c\u064f\u0645\u06c1",
               "\u0628\u0679\u0648\u0627\u0631",
            };
        final String[] metaValue_DayAbbreviations = new String[] {
               "\u0622\u062a\u06be\u0648\u0627\u0631",
               "\u0698\u0654\u0646\u065b\u062f\u0655\u0631\u0648\u0627\u0631",
               "\u0628\u0648\u065a\u0645\u0648\u0627\u0631",
               "\u0628\u0648\u062f\u0648\u0627\u0631",
               "\u0628\u0631\u065b\u066e\u06ea\u0633\u0648\u0627\u0631",
               "\u062c\u064f\u0645\u06c1",
               "\u0628\u0679\u0648\u0627\u0631",
            };
        final String[] metaValue_DayNarrows = new String[] {
               "\u0627",
               "\u0698",
               "\u0628",
               "\u0628",
               "\u0628",
               "\u062c",
               "\u0628",
            };
        final String[] metaValue_QuarterNames = new String[] {
               "\u06af\u06c4\u0691\u0646\u06cc\u064f\u06a9 \u0698\u06c4\u0628\u0627\u06af",
               "\u062f\u0648\u065a\u06cc\u0650\u0645 \u0698\u06c4\u0628\u0627\u06af",
               "\u062a\u0631\u065b\u06cc\u0650\u0645 \u0698\u06c4\u0628\u0627\u06af",
               "\u0698\u0648\u0657\u0631\u0650\u0645 \u0698\u06c4\u0628\u0627\u06af",
            };
        final String[] metaValue_QuarterAbbreviations = new String[] {
               "\u0698\u06c4\u0628\u0627\u06af",
               "\u062f\u0648\u065a\u06cc\u0650\u0645 \u0698\u06c4\u0628\u0627\u06af",
               "\u062a\u0631\u065b\u06cc\u0650\u0645 \u0698\u06c4\u0628\u0627\u06af",
               "\u0698\u0648\u0657\u0631\u0650\u0645 \u0698\u06c4\u0628\u0627\u06af",
            };
        final String[] metaValue_Eras = new String[] {
               "\u0628\u06cc \u0633\u06cc",
               "\u0627\u06d2 \u0688\u06cc",
            };
        final String[] metaValue_TimePatterns = new String[] {
               "h:mm:ss a zzzz",
               "h:mm:ss a z",
               "h:mm:ss a",
               "h:mm a",
            };
        final String[] metaValue_java_time_buddhist_DatePatterns = new String[] {
               "EEEE, MMMM d, Gy",
               "MMMM d, Gy",
               "MMM d, Gy",
               "M/d/Gy",
            };
        final String[] metaValue_buddhist_DatePatterns = new String[] {
               "EEEE, MMMM d, GGGGy",
               "MMMM d, GGGGy",
               "MMM d, GGGGy",
               "M/d/GGGGy",
            };
        final String metaValue_calendarname_gregorian = "\u06af\u0631\u06af\u0648\u0631\u06cc\u064e\u0646 \u06a9\u06cc\u0644\u0646\u0691\u064e\u0631";
        final Object[][] data = new Object[][] {
            { "MonthNames", metaValue_MonthNames },
            { "field.year", "\u0624\u0631\u06cc" },
            { "islamic.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "roc.DayAbbreviations", metaValue_DayAbbreviations },
            { "standalone.DayNarrows", metaValue_DayNarrows },
            { "java.time.japanese.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "standalone.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "roc.QuarterNames", metaValue_QuarterNames },
            { "TimePatterns", metaValue_TimePatterns },
            { "islamic.DayNarrows", metaValue_DayNarrows },
            { "field.zone", "\u0632\u0648\u0646" },
            { "roc.MonthNarrows", metaValue_MonthNarrows },
            { "calendarname.islamic-civil", "\u0627\u0650\u0633\u0644\u0672\u0645\u06cc \u0627\u0650\u062c\u062a\u0645\u0672\u06cc\u06cc \u06a9\u06cc\u0644\u0646\u0691\u064e\u0631" },
            { "japanese.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "japanese.TimePatterns", metaValue_TimePatterns },
            { "narrow.Eras", metaValue_Eras },
            { "DefaultNumberingSystem", "arabext" },
            { "calendarname.japanese", "\u062c\u0627\u067e\u0672\u0646\u06cd \u06a9\u06cc\u0644\u0646\u0691\u064e\u0631" },
            { "Eras", metaValue_Eras },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "roc.DayNames", metaValue_DayNames },
            { "standalone.DayAbbreviations", metaValue_DayAbbreviations },
            { "roc.MonthAbbreviations", metaValue_MonthNames },
            { "islamic.QuarterNames", metaValue_QuarterNames },
            { "long.Eras",
                new String[] {
                    "\u0642\u0628\u0655\u0644 \u0645\u0633\u06cc\u0656\u062d",
                    "\u0639\u06cc\u0656\u0633\u0648\u06cc \u0633\u0646\u06c1\u0655",
                }
            },
            { "islamic.DayNames", metaValue_DayNames },
            { "java.time.islamic.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "buddhist.MonthAbbreviations", metaValue_MonthNames },
            { "field.weekday", "\u06c1\u0641\u062a\u064f\u06a9 \u062f\u06c4\u06c1" },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}",
                    "{1} {0}",
                    "{1} {0}",
                    "{1} {0}",
                }
            },
            { "latn.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "0",
                    "#",
                    "\u200e-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                }
            },
            { "MonthNarrows", metaValue_MonthNarrows },
            { "japanese.DatePatterns", metaValue_buddhist_DatePatterns },
            { "japanese.MonthAbbreviations", metaValue_MonthNames },
            { "buddhist.DayNames", metaValue_DayNames },
            { "field.minute", "\u0645\u0650\u0646\u064e\u0679" },
            { "field.era", "\u062f\u0648\u0631" },
            { "islamic.DayAbbreviations", metaValue_DayAbbreviations },
            { "field.dayperiod", "\u0635\u0628\u062d/\u0631\u0627\u062a" },
            { "standalone.MonthNarrows", metaValue_MonthNarrows },
            { "japanese.QuarterNames", metaValue_QuarterNames },
            { "buddhist.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "calendarname.roc", "\u062c\u0645\u0648\u0657\u0631\u06cc\u0672\u062a\u06cc \u0686\u06cc\u0656\u0646\u06cc \u06a9\u06cc\u0644\u064e\u0646\u0691\u064e\u0631" },
            { "islamic.DatePatterns", metaValue_buddhist_DatePatterns },
            { "roc.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "japanese.DayNames", metaValue_DayNames },
            { "japanese.DayAbbreviations", metaValue_DayAbbreviations },
            { "DayNames", metaValue_DayNames },
            { "field.month", "\u0631\u066e\u06ea\u062a\u06be" },
            { "buddhist.DatePatterns", metaValue_buddhist_DatePatterns },
            { "field.second", "\u0633\u066e\u06ea\u06a9\u064e\u0646\u0691" },
            { "roc.MonthNames", metaValue_MonthNames },
            { "field.week", "\u06c1\u0641\u062a\u06c1\u0655" },
            { "DayAbbreviations", metaValue_DayAbbreviations },
            { "DayNarrows", metaValue_DayNarrows },
            { "NumberPatterns",
                new String[] {
                    "#,##,##0.###",
                    "\u00a4\u00a0#,##,##0.00",
                    "#,##,##0%",
                }
            },
            { "roc.DatePatterns", metaValue_buddhist_DatePatterns },
            { "buddhist.MonthNarrows", metaValue_MonthNarrows },
            { "buddhist.QuarterNames", metaValue_QuarterNames },
            { "calendarname.islamic", "\u0627\u0650\u0633\u0644\u0672\u0645\u06cc \u06a9\u06cc\u0644\u0646\u0691\u064e\u0631" },
            { "roc.DayNarrows", metaValue_DayNarrows },
            { "java.time.roc.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "java.time.buddhist.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "calendarname.gregorian", metaValue_calendarname_gregorian },
            { "DatePatterns",
                new String[] {
                    "EEEE, MMMM d, y",
                    "MMMM d, y",
                    "MMM d, y",
                    "M/d/yy",
                }
            },
            { "buddhist.DayAbbreviations", metaValue_DayAbbreviations },
            { "islamic.TimePatterns", metaValue_TimePatterns },
            { "MonthAbbreviations", metaValue_MonthNames },
            { "standalone.DayNames", metaValue_DayNames },
            { "field.hour", "\u06af\u0672\u0646\u065b\u0679\u06c1\u0655" },
            { "buddhist.TimePatterns", metaValue_TimePatterns },
            { "calendarname.buddhist", "\u0628\u064f\u062f\u064e\u0646 \u06c1\u064f\u0646\u065b\u062f \u06a9\u06cc\u0644\u0646\u0691\u064e\u0631" },
            { "standalone.MonthNames", metaValue_MonthNames },
            { "standalone.MonthAbbreviations", metaValue_MonthNames },
            { "buddhist.DayNarrows", metaValue_DayNarrows },
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
