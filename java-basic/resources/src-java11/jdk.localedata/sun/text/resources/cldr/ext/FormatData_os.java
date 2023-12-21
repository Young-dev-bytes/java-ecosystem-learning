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

public class FormatData_os extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthNames = new String[] {
               "\u044f\u043d\u0432\u0430\u0440\u044b",
               "\u0444\u0435\u0432\u0440\u0430\u043b\u044b",
               "\u043c\u0430\u0440\u0442\u044a\u0438\u0439\u044b",
               "\u0430\u043f\u0440\u0435\u043b\u044b",
               "\u043c\u0430\u0439\u044b",
               "\u0438\u044e\u043d\u044b",
               "\u0438\u044e\u043b\u044b",
               "\u0430\u0432\u0433\u0443\u0441\u0442\u044b",
               "\u0441\u0435\u043d\u0442\u044f\u0431\u0440\u044b",
               "\u043e\u043a\u0442\u044f\u0431\u0440\u044b",
               "\u043d\u043e\u044f\u0431\u0440\u044b",
               "\u0434\u0435\u043a\u0430\u0431\u0440\u044b",
               "",
            };
        final String[] metaValue_MonthAbbreviations = new String[] {
               "\u044f\u043d\u0432.",
               "\u0444\u0435\u0432.",
               "\u043c\u0430\u0440.",
               "\u0430\u043f\u0440.",
               "\u043c\u0430\u0439\u044b",
               "\u0438\u044e\u043d\u044b",
               "\u0438\u044e\u043b\u044b",
               "\u0430\u0432\u0433.",
               "\u0441\u0435\u043d.",
               "\u043e\u043a\u0442.",
               "\u043d\u043e\u044f.",
               "\u0434\u0435\u043a.",
               "",
            };
        final String[] metaValue_MonthNarrows = new String[] {
               "\u042f",
               "\u0424",
               "\u041c",
               "\u0410",
               "\u041c",
               "\u0418",
               "\u0418",
               "\u0410",
               "\u0421",
               "\u041e",
               "\u041d",
               "\u0414",
               "",
            };
        final String[] metaValue_DayNames = new String[] {
               "\u0445\u0443\u044b\u0446\u0430\u0443\u0431\u043e\u043d",
               "\u043a\u044a\u0443\u044b\u0440\u0438\u0441\u04d5\u0440",
               "\u0434\u044b\u0446\u0446\u04d5\u0433",
               "\u04d5\u0440\u0442\u044b\u0446\u0446\u04d5\u0433",
               "\u0446\u044b\u043f\u043f\u04d5\u0440\u04d5\u043c",
               "\u043c\u0430\u0439\u0440\u04d5\u043c\u0431\u043e\u043d",
               "\u0441\u0430\u0431\u0430\u0442",
            };
        final String[] metaValue_DayAbbreviations = new String[] {
               "\u0445\u0446\u0431",
               "\u043a\u0440\u0441",
               "\u0434\u0446\u0433",
               "\u04d5\u0440\u0442",
               "\u0446\u043f\u0440",
               "\u043c\u0440\u0431",
               "\u0441\u0431\u0442",
            };
        final String[] metaValue_DayNarrows = new String[] {
               "\u0425",
               "\u041a",
               "\u0414",
               "\u04d4",
               "\u0426",
               "\u041c",
               "\u0421",
            };
        final String[] metaValue_QuarterNames = new String[] {
               "1-\u0430\u0433 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
               "2-\u0430\u0433 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
               "3-\u0430\u0433 \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
               "4-\u04d5\u043c \u043a\u0432\u0430\u0440\u0442\u0430\u043b",
            };
        final String[] metaValue_QuarterAbbreviations = new String[] {
               "1-\u0430\u0433 \u043a\u0432.",
               "2-\u0430\u0433 \u043a\u0432.",
               "3-\u0430\u0433 \u043a\u0432.",
               "4-\u04d5\u043c \u043a\u0432.",
            };
        final String[] metaValue_AmPmMarkers = new String[] {
               "\u04d5\u043c\u0431\u0438\u0441\u0431\u043e\u043d\u044b \u0440\u0430\u0437\u043c\u04d5",
               "\u04d5\u043c\u0431\u0438\u0441\u0431\u043e\u043d\u044b \u0444\u04d5\u0441\u0442\u04d5",
            };
        final String[] metaValue_long_Eras = new String[] {
               "\u043d.\u0434.\u0430.",
               "\u043d.\u0434.",
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
               "EEEE, d MMMM, y '\u0430\u0437' G",
               "d MMMM, y '\u0430\u0437' G",
               "dd MMM y '\u0430\u0437' G",
               "dd.MM.yy GGGGG",
            };
        final String[] metaValue_buddhist_DatePatterns = new String[] {
               "EEEE, d MMMM, y '\u0430\u0437' GGGG",
               "d MMMM, y '\u0430\u0437' GGGG",
               "dd MMM y '\u0430\u0437' GGGG",
               "dd.MM.yy G",
            };
        final String metaValue_calendarname_gregorian = "\u0413\u0440\u0435\u0433\u043e\u0440\u0438\u0430\u043d \u043a\u044a\u04d5\u043b\u0438\u043d\u0434\u0430\u0440";
        final Object[][] data = new Object[][] {
            { "MonthNames", metaValue_MonthNames },
            { "field.year", "\u0410\u0437" },
            { "islamic.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "roc.DayAbbreviations", metaValue_DayAbbreviations },
            { "standalone.DayNarrows", metaValue_DayNarrows },
            { "japanese.AmPmMarkers", metaValue_AmPmMarkers },
            { "islamic.AmPmMarkers", metaValue_AmPmMarkers },
            { "AmPmMarkers", metaValue_AmPmMarkers },
            { "java.time.japanese.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "standalone.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "roc.QuarterNames", metaValue_QuarterNames },
            { "TimePatterns", metaValue_TimePatterns },
            { "islamic.DayNarrows", metaValue_DayNarrows },
            { "field.zone", "\u0420\u04d5\u0441\u0442\u04d5\u0434\u0436\u044b \u0437\u043e\u043d\u04d5" },
            { "roc.MonthNarrows", metaValue_MonthNarrows },
            { "japanese.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "japanese.TimePatterns", metaValue_TimePatterns },
            { "narrow.Eras", metaValue_long_Eras },
            { "Eras", metaValue_long_Eras },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "roc.DayNames", metaValue_DayNames },
            { "standalone.DayAbbreviations",
                new String[] {
                    "\u0425\u0446\u0431",
                    "\u041a\u0440\u0441",
                    "\u0414\u0446\u0433",
                    "\u04d4\u0440\u0442",
                    "\u0426\u043f\u0440",
                    "\u041c\u0440\u0431",
                    "\u0421\u0431\u0442",
                }
            },
            { "roc.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "islamic.QuarterNames", metaValue_QuarterNames },
            { "long.Eras", metaValue_long_Eras },
            { "islamic.DayNames", metaValue_DayNames },
            { "java.time.islamic.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "buddhist.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "field.weekday", "\u041a\u044a\u0443\u044b\u0440\u0438\u0439\u044b \u0431\u043e\u043d" },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "DateTimePatterns",
                new String[] {
                    "{1}, {0}",
                    "{1}, {0}",
                    "{1}, {0}",
                    "{1}, {0}",
                }
            },
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
                    "\u041d\u041d",
                }
            },
            { "MonthNarrows", metaValue_MonthNarrows },
            { "japanese.DatePatterns", metaValue_buddhist_DatePatterns },
            { "japanese.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "buddhist.DayNames", metaValue_DayNames },
            { "field.minute", "\u041c\u0438\u043d\u0443\u0442" },
            { "field.era", "\u0414\u0443\u0433" },
            { "islamic.DayAbbreviations", metaValue_DayAbbreviations },
            { "buddhist.AmPmMarkers", metaValue_AmPmMarkers },
            { "field.dayperiod", "\u0411\u043e\u043d\u044b \u043f\u0435\u0440\u0438\u043e\u0434" },
            { "standalone.MonthNarrows", metaValue_MonthNarrows },
            { "japanese.QuarterNames", metaValue_QuarterNames },
            { "buddhist.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "islamic.DatePatterns", metaValue_buddhist_DatePatterns },
            { "roc.QuarterAbbreviations", metaValue_QuarterAbbreviations },
            { "japanese.DayNames", metaValue_DayNames },
            { "japanese.DayAbbreviations", metaValue_DayAbbreviations },
            { "DayNames", metaValue_DayNames },
            { "field.month", "\u041c\u04d5\u0439" },
            { "buddhist.DatePatterns", metaValue_buddhist_DatePatterns },
            { "field.second", "\u0421\u0435\u043a\u0443\u043d\u0434" },
            { "roc.MonthNames", metaValue_MonthNames },
            { "field.week", "\u041a\u044a\u0443\u044b\u0440\u0438" },
            { "DayAbbreviations", metaValue_DayAbbreviations },
            { "DayNarrows", metaValue_DayNarrows },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4\u00a0#,##0.00",
                    "#,##0%",
                }
            },
            { "roc.DatePatterns", metaValue_buddhist_DatePatterns },
            { "buddhist.MonthNarrows", metaValue_MonthNarrows },
            { "buddhist.QuarterNames", metaValue_QuarterNames },
            { "islamic.QuarterNarrows", metaValue_buddhist_QuarterNarrows },
            { "roc.DayNarrows", metaValue_DayNarrows },
            { "roc.AmPmMarkers", metaValue_AmPmMarkers },
            { "java.time.roc.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "java.time.buddhist.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "calendarname.gregorian", metaValue_calendarname_gregorian },
            { "DatePatterns",
                new String[] {
                    "EEEE, d MMMM, y '\u0430\u0437'",
                    "d MMMM, y '\u0430\u0437'",
                    "dd MMM y '\u0430\u0437'",
                    "dd.MM.yy",
                }
            },
            { "buddhist.DayAbbreviations", metaValue_DayAbbreviations },
            { "islamic.TimePatterns", metaValue_TimePatterns },
            { "MonthAbbreviations", metaValue_MonthAbbreviations },
            { "standalone.DayNames",
                new String[] {
                    "\u0425\u0443\u044b\u0446\u0430\u0443\u0431\u043e\u043d",
                    "\u041a\u044a\u0443\u044b\u0440\u0438\u0441\u04d5\u0440",
                    "\u0414\u044b\u0446\u0446\u04d5\u0433",
                    "\u04d4\u0440\u0442\u044b\u0446\u0446\u04d5\u0433",
                    "\u0426\u044b\u043f\u043f\u04d5\u0440\u04d5\u043c",
                    "\u041c\u0430\u0439\u0440\u04d5\u043c\u0431\u043e\u043d",
                    "\u0421\u0430\u0431\u0430\u0442",
                }
            },
            { "field.hour", "\u0421\u0430\u0445\u0430\u0442" },
            { "buddhist.TimePatterns", metaValue_TimePatterns },
            { "standalone.MonthNames",
                new String[] {
                    "\u042f\u043d\u0432\u0430\u0440\u044c",
                    "\u0424\u0435\u0432\u0440\u0430\u043b\u044c",
                    "\u041c\u0430\u0440\u0442\u044a\u0438",
                    "\u0410\u043f\u0440\u0435\u043b\u044c",
                    "\u041c\u0430\u0439",
                    "\u0418\u044e\u043d\u044c",
                    "\u0418\u044e\u043b\u044c",
                    "\u0410\u0432\u0433\u0443\u0441\u0442",
                    "\u0421\u0435\u043d\u0442\u044f\u0431\u0440\u044c",
                    "\u041e\u043a\u0442\u044f\u0431\u0440\u044c",
                    "\u041d\u043e\u044f\u0431\u0440\u044c",
                    "\u0414\u0435\u043a\u0430\u0431\u0440\u044c",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "\u042f\u043d\u0432.",
                    "\u0424\u0435\u0432\u0440.",
                    "\u041c\u0430\u0440\u0442.",
                    "\u0410\u043f\u0440.",
                    "\u041c\u0430\u0439",
                    "\u0418\u044e\u043d\u044c",
                    "\u0418\u044e\u043b\u044c",
                    "\u0410\u0432\u0433.",
                    "\u0421\u0435\u043d\u0442.",
                    "\u041e\u043a\u0442.",
                    "\u041d\u043e\u044f\u0431.",
                    "\u0414\u0435\u043a.",
                    "",
                }
            },
            { "timezone.regionFormat", "{0} \u0440\u04d5\u0441\u0442\u04d5\u0433" },
            { "buddhist.DayNarrows", metaValue_DayNarrows },
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
