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

package sun.util.resources.cldr.ext;

import sun.util.resources.TimeZoneNamesBundle;

public class TimeZoneNames_tt extends TimeZoneNamesBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] GMT = new String[] {
               "\u0413\u0440\u0438\u043d\u0432\u0438\u0447 \u0443\u0440\u0442\u0430\u0447\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "",
               "",
               "",
               "",
            };
        final String[] ETC_UTC = new String[] {
               "\u0411\u04e9\u0442\u0435\u043d\u0434\u04e9\u043d\u044c\u044f \u043a\u0438\u043b\u0435\u0448\u0442\u0435\u0440\u0435\u043b\u0433\u04d9\u043d \u0432\u0430\u043a\u044b\u0442\u044b",
               "UTC",
               "",
               "",
               "",
               "",
            };
        final String[] Atlantic = new String[] {
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0433\u0430\u0434\u04d9\u0442\u0438 \u0430\u0442\u043b\u0430\u043d\u0442\u0438\u043a \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0497\u04d9\u0439\u0433\u0435 \u0430\u0442\u043b\u0430\u043d\u0442\u0438\u043a \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0430\u0442\u043b\u0430\u043d\u0442\u0438\u043a \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] Europe_Central = new String[] {
               "\u0433\u0430\u0434\u04d9\u0442\u0438 \u04ae\u0437\u04d9\u043a \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0497\u04d9\u0439\u0433\u0435 \u04ae\u0437\u04d9\u043a \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u04ae\u0437\u04d9\u043a \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] Europe_Eastern = new String[] {
               "\u0433\u0430\u0434\u04d9\u0442\u0438 \u041a\u04e9\u043d\u0447\u044b\u0433\u044b\u0448 \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0497\u04d9\u0439\u0433\u0435 \u041a\u04e9\u043d\u0447\u044b\u0433\u044b\u0448 \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u041a\u04e9\u043d\u0447\u044b\u0433\u044b\u0448 \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] Europe_Western = new String[] {
               "\u0433\u0430\u0434\u04d9\u0442\u0438 \u041a\u04e9\u043d\u0431\u0430\u0442\u044b\u0448 \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0497\u04d9\u0439\u0433\u0435 \u041a\u04e9\u043d\u0431\u0430\u0442\u044b\u0448 \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u041a\u04e9\u043d\u0431\u0430\u0442\u044b\u0448 \u0415\u0432\u0440\u043e\u043f\u0430 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] America_Central = new String[] {
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0433\u0430\u0434\u04d9\u0442\u0438 \u04af\u0437\u04d9\u043a \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0497\u04d9\u0439\u0433\u0435 \u04af\u0437\u04d9\u043a \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u04af\u0437\u04d9\u043a \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] America_Eastern = new String[] {
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0433\u0430\u0434\u04d9\u0442\u0438 \u043a\u04e9\u043d\u0447\u044b\u0433\u044b\u0448 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0497\u04d9\u0439\u0433\u0435 \u043a\u04e9\u043d\u0447\u044b\u0433\u044b\u0448 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u043a\u04e9\u043d\u0447\u044b\u0433\u044b\u0448 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] America_Pacific = new String[] {
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0433\u0430\u0434\u04d9\u0442\u0438 \u0422\u044b\u043d \u043e\u043a\u0435\u0430\u043d \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0497\u04d9\u0439\u0433\u0435 \u0422\u044b\u043d \u043e\u043a\u0435\u0430\u043d \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0422\u044b\u043d \u043e\u043a\u0435\u0430\u043d \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final String[] America_Mountain = new String[] {
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0433\u0430\u0434\u04d9\u0442\u0438 \u0442\u0430\u0443 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0497\u04d9\u0439\u0433\u0435 \u0442\u0430\u0443 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
               "\u0422\u04e9\u043d\u044c\u044f\u043a \u0410\u043c\u0435\u0440\u0438\u043a\u0430 \u0442\u0430\u0443 \u0432\u0430\u043a\u044b\u0442\u044b",
               "",
            };
        final Object[][] data = new Object[][] {
            { "America/Los_Angeles", America_Pacific },
            { "America/Denver", America_Mountain },
            { "America/Phoenix", America_Mountain },
            { "America/Chicago", America_Central },
            { "America/New_York", America_Eastern },
            { "America/Indianapolis", America_Eastern },
            { "America/Halifax", Atlantic },
            { "Europe/Paris", Europe_Central },
            { "GMT", GMT },
            { "Africa/Casablanca", Europe_Western },
            { "Europe/Bucharest", Europe_Eastern },
            { "UTC", ETC_UTC },
            { "ART", Europe_Eastern },
            { "CST", America_Central },
            { "ECT", Europe_Central },
            { "EST", America_Eastern },
            { "MST", America_Mountain },
            { "PNT", America_Mountain },
            { "PRT", Atlantic },
            { "PST", America_Pacific },
            { "CST6CDT", America_Central },
            { "EST5EDT", America_Eastern },
            { "Etc/GMT", GMT },
            { "Etc/UTC", ETC_UTC },
            { "MST7MDT", America_Mountain },
            { "PST8PDT", America_Pacific },
            { "Asia/Gaza", Europe_Eastern },
            { "Asia/Amman", Europe_Eastern },
            { "Africa/Lome", GMT },
            { "Asia/Beirut", Europe_Eastern },
            { "Asia/Hebron", Europe_Eastern },
            { "Europe/Kiev", Europe_Eastern },
            { "Europe/Oslo", Europe_Central },
            { "Europe/Riga", Europe_Eastern },
            { "Europe/Rome", Europe_Central },
            { "Africa/Accra", GMT },
            { "Africa/Cairo", Europe_Eastern },
            { "Africa/Ceuta", Europe_Central },
            { "Africa/Dakar", GMT },
            { "Africa/Tunis", Europe_Central },
            { "Asia/Nicosia", Europe_Eastern },
            { "Europe/Malta", Europe_Central },
            { "Europe/Sofia", Europe_Eastern },
            { "Europe/Vaduz", Europe_Central },
            { "SystemV/AST4", Atlantic },
            { "SystemV/CST6", America_Central },
            { "SystemV/EST5", America_Eastern },
            { "SystemV/MST7", America_Mountain },
            { "Africa/Bamako", GMT },
            { "Africa/Banjul", GMT },
            { "Africa/Bissau", GMT },
            { "America/Aruba", Atlantic },
            { "America/Boise", America_Mountain },
            { "America/Thule", Atlantic },
            { "Asia/Damascus", Europe_Eastern },
            { "Europe/Athens", Europe_Eastern },
            { "Europe/Berlin", Europe_Central },
            { "Europe/Dublin", GMT },
            { "Europe/Jersey", GMT },
            { "Europe/Lisbon", Europe_Western },
            { "Europe/London", GMT },
            { "Europe/Madrid", Europe_Central },
            { "Europe/Monaco", Europe_Central },
            { "Europe/Prague", Europe_Central },
            { "Europe/Skopje", Europe_Central },
            { "Europe/Tirane", Europe_Central },
            { "Europe/Vienna", Europe_Central },
            { "Europe/Warsaw", Europe_Central },
            { "Europe/Zagreb", Europe_Central },
            { "Europe/Zurich", Europe_Central },
            { "Africa/Abidjan", GMT },
            { "Africa/Algiers", Europe_Central },
            { "Africa/Conakry", GMT },
            { "Africa/Tripoli", Europe_Eastern },
            { "America/Belize", America_Central },
            { "America/Cancun", America_Eastern },
            { "America/Cayman", America_Eastern },
            { "America/Dawson", America_Pacific },
            { "America/Inuvik", America_Mountain },
            { "America/Merida", America_Central },
            { "America/Nassau", America_Eastern },
            { "America/Panama", America_Eastern },
            { "America/Regina", America_Central },
            { "Europe/Andorra", Europe_Central },
            { "Europe/Belfast", GMT },
            { "Europe/Tallinn", Europe_Eastern },
            { "Europe/Vatican", Europe_Central },
            { "Europe/Vilnius", Europe_Eastern },
            { "Africa/El_Aaiun", Europe_Western },
            { "Africa/Freetown", GMT },
            { "Africa/Monrovia", GMT },
            { "Africa/Timbuktu", GMT },
            { "America/Antigua", Atlantic },
            { "America/Creston", America_Mountain },
            { "America/Curacao", Atlantic },
            { "America/Detroit", America_Eastern },
            { "America/Grenada", Atlantic },
            { "America/Iqaluit", America_Eastern },
            { "America/Jamaica", America_Eastern },
            { "America/Managua", America_Central },
            { "America/Marigot", Atlantic },
            { "America/Moncton", Atlantic },
            { "America/Nipigon", America_Eastern },
            { "America/Ojinaga", America_Central },
            { "America/Tijuana", America_Pacific },
            { "America/Toronto", America_Eastern },
            { "America/Tortola", Atlantic },
            { "Atlantic/Canary", Europe_Western },
            { "Atlantic/Faeroe", Europe_Western },
            { "Europe/Belgrade", Europe_Central },
            { "Europe/Brussels", Europe_Central },
            { "Europe/Budapest", Europe_Central },
            { "Europe/Busingen", Europe_Central },
            { "Europe/Chisinau", Europe_Eastern },
            { "Europe/Guernsey", GMT },
            { "Europe/Helsinki", Europe_Eastern },
            { "Europe/Sarajevo", Europe_Central },
            { "Europe/Uzhgorod", Europe_Eastern },
            { "SystemV/AST4ADT", Atlantic },
            { "SystemV/CST6CDT", America_Central },
            { "SystemV/EST5EDT", America_Eastern },
            { "SystemV/MST7MDT", America_Mountain },
            { "SystemV/PST8PDT", America_Pacific },
            { "America/Anguilla", Atlantic },
            { "America/Barbados", Atlantic },
            { "America/Dominica", Atlantic },
            { "America/Edmonton", America_Mountain },
            { "America/Montreal", America_Eastern },
            { "America/Resolute", America_Central },
            { "America/Shiprock", America_Mountain },
            { "America/St_Kitts", Atlantic },
            { "America/St_Lucia", Atlantic },
            { "America/Winnipeg", America_Central },
            { "Antarctica/Troll", GMT },
            { "Atlantic/Bermuda", Atlantic },
            { "Atlantic/Madeira", Europe_Western },
            { "Europe/Amsterdam", Europe_Central },
            { "Europe/Gibraltar", Europe_Central },
            { "Europe/Ljubljana", Europe_Central },
            { "Europe/Mariehamn", Europe_Eastern },
            { "Europe/Podgorica", Europe_Central },
            { "Europe/Stockholm", Europe_Central },
            { "Africa/Nouakchott", GMT },
            { "America/Chihuahua", America_Central },
            { "America/Glace_Bay", Atlantic },
            { "America/Goose_Bay", Atlantic },
            { "America/Guatemala", America_Central },
            { "America/Matamoros", America_Central },
            { "America/Menominee", America_Central },
            { "America/Monterrey", America_Central },
            { "America/St_Thomas", Atlantic },
            { "America/Vancouver", America_Pacific },
            { "Europe/Bratislava", Europe_Central },
            { "Europe/Copenhagen", Europe_Central },
            { "Europe/Luxembourg", Europe_Central },
            { "Europe/San_Marino", Europe_Central },
            { "Europe/Zaporozhye", Europe_Eastern },
            { "Africa/Ouagadougou", GMT },
            { "America/Costa_Rica", America_Central },
            { "America/Grand_Turk", America_Eastern },
            { "America/Guadeloupe", Atlantic },
            { "America/Kralendijk", Atlantic },
            { "America/Louisville", America_Eastern },
            { "America/Martinique", Atlantic },
            { "America/Montserrat", Atlantic },
            { "America/St_Vincent", Atlantic },
            { "America/Whitehorse", America_Pacific },
            { "Atlantic/Jan_Mayen", Europe_Central },
            { "Atlantic/Reykjavik", GMT },
            { "Atlantic/St_Helena", GMT },
            { "Europe/Isle_of_Man", GMT },
            { "Europe/Kaliningrad", Europe_Eastern },
            { "America/El_Salvador", America_Central },
            { "America/Fort_Nelson", America_Mountain },
            { "America/Mexico_City", America_Central },
            { "America/Pangnirtung", America_Eastern },
            { "America/Puerto_Rico", Atlantic },
            { "America/Rainy_River", America_Central },
            { "America/Tegucigalpa", America_Central },
            { "America/Thunder_Bay", America_Eastern },
            { "America/Yellowknife", America_Mountain },
            { "Arctic/Longyearbyen", Europe_Central },
            { "America/Blanc-Sablon", Atlantic },
            { "America/Danmarkshavn", GMT },
            { "America/Dawson_Creek", America_Mountain },
            { "America/Indiana/Knox", America_Central },
            { "America/Rankin_Inlet", America_Central },
            { "America/Cambridge_Bay", America_Mountain },
            { "America/Coral_Harbour", America_Eastern },
            { "America/Indiana/Vevay", America_Eastern },
            { "America/Lower_Princes", Atlantic },
            { "America/Port_of_Spain", Atlantic },
            { "America/Santo_Domingo", Atlantic },
            { "America/St_Barthelemy", Atlantic },
            { "America/Swift_Current", America_Central },
            { "America/Bahia_Banderas", America_Central },
            { "America/Port-au-Prince", America_Eastern },
            { "America/Indiana/Marengo", America_Eastern },
            { "America/Indiana/Winamac", America_Eastern },
            { "America/Indiana/Tell_City", America_Central },
            { "America/Indiana/Vincennes", America_Eastern },
            { "America/Indiana/Petersburg", America_Eastern },
            { "America/Kentucky/Monticello", America_Eastern },
            { "America/North_Dakota/Beulah", America_Central },
            { "America/North_Dakota/Center", America_Central },
            { "timezone.excity.Etc/Unknown", "\u0431\u0438\u043b\u0433\u0435\u0441\u0435\u0437 \u0448\u04d9\u04bb\u04d9\u0440" },
            { "America/North_Dakota/New_Salem", America_Central },
        };
        return data;
    }
}
