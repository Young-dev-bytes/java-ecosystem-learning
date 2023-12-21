/*
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.USE_SUNDAY_QUICK_SEARCH;

final class SearchAlgorithm {

    static abstract class Forward {
        abstract String getName();
        abstract int search(Regex regex, char[] text, int textP, int textEnd, int textRange);
    }

    static abstract class Backward {
        abstract int search(Regex regex, char []text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_);
    }

    private static boolean lowerCaseMatch(char[] t, int tPp, int tEnd, char[] chars, int pp, int end){
        for (int tP = tPp, p = pp; tP < tEnd;) {
            if (t[tP++] != EncodingHelper.toLowerCase(chars[p++])) {
                return false;
            }
        }
        return true;
    }

    static final Forward SLOW_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "EXACT_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int end = textEnd;
            end -= targetEnd - targetP - 1;
            if (end > textRange) {
                end = textRange;
            }
            int s = textP;

            while (s < end) {
                if (text[s] == target[targetP]) {
                    int p = s + 1;
                    int t = targetP + 1;
                    while (t < targetEnd) {
                        if (target[t] != text[p++]) {
                            break;
                        }
                        t++;
                    }

                    if (t == targetEnd) {
                        return s;
                    }
                }

                s++;
            }
            return -1;
        }
    };

    static final Backward SLOW_BACKWARD = new Backward() {
        @Override
        final int search(Regex regex, char[]text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int s = textEnd;
            s -= targetEnd - targetP;

            if (s > textStart) {
                s = textStart;
            }

            while (s >= textP) {
                if (text[s] == target[targetP]) {
                    int p = s + 1;
                    int t = targetP + 1;
                    while (t < targetEnd) {
                        if (target[t] != text[p++]) {
                            break;
                        }
                        t++;
                    }
                    if (t == targetEnd) {
                        return s;
                    }
                }
                s--;
            }
            return -1;
        }
    };

    static final Forward SLOW_IC_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "EXACT_IC_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int end = textEnd;
            end -= targetEnd - targetP - 1;
            if (end > textRange) {
                end = textRange;
            }
            int s = textP;

            while (s < end) {
                if (lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) {
                    return s;
                }
                s++;
            }
            return -1;
        }
    };

    static final Backward SLOW_IC_BACKWARD = new Backward() {
        @Override
        final int search(Regex regex, char[]text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int s = textEnd;
            s -= targetEnd - targetP;

            if (s > textStart) {
                s = textStart;
            }

            while (s >= textP) {
                if (lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) {
                    return s;
                }
                s = EncodingHelper.prevCharHead(adjustText, s);
            }
            return -1;
        }
    };

    static final Forward BM_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "EXACT_BM_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int end, s;
            int tail = targetEnd - 1;
            if (USE_SUNDAY_QUICK_SEARCH) {
                int tlen1 = tail - targetP;
                end = textRange + tlen1;
                s = textP + tlen1;
            } else {
                end = textRange + (targetEnd - targetP) - 1;
                s = textP + (targetEnd - targetP) - 1;
            }
            if (end > textEnd) {
                end = textEnd;
            }

            if (Config.USE_BYTE_MAP || regex.intMap == null) {
                while (s < end) {
                    int p = s;
                    int t = tail;

                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return p;
                        }
                        p--; t--;
                    }
                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    s += regex.map[text[USE_SUNDAY_QUICK_SEARCH ? s + 1 : s] & 0xff];
                }
            } else { /* see int_map[] */
                while (s < end) {
                    int p = s;
                    int t = tail;

                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return p;
                        }
                        p--; t--;
                    }

                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    s += regex.intMap[text[USE_SUNDAY_QUICK_SEARCH ? s + 1 : s] & 0xff];
                }
            }
            return -1;
        }
    };

    static final Backward BM_BACKWARD = new Backward() {
        private static final int BM_BACKWARD_SEARCH_LENGTH_THRESHOLD = 100;
        @Override
        final int search(Regex regex, char[]text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            if (Config.USE_INT_MAP_BACKWARD) {
                char[]target = regex.exact;
                int targetP = regex.exactP;
                int targetEnd = regex.exactEnd;

                if (regex.intMapBackward == null) {
                    if (s_ - range_ < BM_BACKWARD_SEARCH_LENGTH_THRESHOLD) {
                        return SLOW_BACKWARD.search(regex, text, textP, adjustText, textEnd, textStart, s_, range_); // goto exact_method;
                    }
                    setBmBackwardSkip(regex, target, targetP, targetEnd);
                }

                int s = textEnd - (targetEnd - targetP);

                if (textStart < s) {
                    s = textStart;
                }

                while (s >= textP) {
                    int p = s;
                    int t = targetP;
                    while (t < targetEnd && text[p] == target[t]) {
                        p++; t++;
                    }
                    if (t == targetEnd) {
                        return s;
                    }

                    s -= regex.intMapBackward[text[s] & 0xff];
                }
                return -1;
            } else {
                return SLOW_BACKWARD.search(regex, text, textP, adjustText, textEnd, textStart, s_, range_); // goto exact_method;
            }
        }


        private void setBmBackwardSkip(Regex regex, char[] chars, final int p, final int end) {
            int[] skip;
            if (regex.intMapBackward == null) {
                regex.intMapBackward = skip = new int[Config.CHAR_TABLE_SIZE];
            } else {
                skip = regex.intMapBackward;
            }

            int len = end - p;

            for (int i = 0; i < Config.CHAR_TABLE_SIZE; i++) {
                skip[i] = len;
            }
            for (int i = len - 1; i > 0; i--) {
                skip[chars[i] & 0xff] = i;
            }
        }
    };

    static final Forward BM_IC_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "EXACT_BM_IC_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int end, s, tlen1;
            int tail = targetEnd - 1;
            if (USE_SUNDAY_QUICK_SEARCH) {
                tlen1 = tail - targetP;
                end = textRange + tlen1;
                s = textP + tlen1;
            } else {
                end = textRange + (targetEnd - targetP) - 1;
                s = textP + (targetEnd - targetP) - 1;
            }
            if (end > textEnd) {
                end = textEnd;
            }

            if (Config.USE_BYTE_MAP || regex.intMap == null) {
                while (s < end) {
                    int p = USE_SUNDAY_QUICK_SEARCH ? s - tlen1 : s - (targetEnd - targetP) + 1;
                    if (lowerCaseMatch(target, targetP, targetEnd, text, p, s + 1)) {
                        return p;
                    }

                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    s += regex.map[text[USE_SUNDAY_QUICK_SEARCH ? s + 1 : s] & 0xff];
                }
            } else { /* see int_map[] */
                while (s < end) {
                    int p = USE_SUNDAY_QUICK_SEARCH ? s - tlen1 : s - (targetEnd - targetP) + 1;
                    if (lowerCaseMatch(target, targetP, targetEnd, text, p, s + 1)) {
                        return p;
                    }

                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    s += regex.intMap[text[USE_SUNDAY_QUICK_SEARCH ? s + 1 : s] & 0xff];
                }
            }
            return -1;
        }
    };

    static final Forward BM_NOT_REV_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "EXACT_BM_NOT_REV_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int tail = targetEnd - 1;
            int tlen1 = tail - targetP;
            int end = textRange;

            if (end + tlen1 > textEnd) {
                end = textEnd - tlen1;
            }
            int s = textP, p, se;

            if (Config.USE_BYTE_MAP || regex.intMap == null) {
                while (s < end) {
                    p = se = s + tlen1;
                    int t = tail;
                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return s;
                        }
                        p--; t--;
                    }
                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    int skip = regex.map[text[USE_SUNDAY_QUICK_SEARCH ? se + 1 : se] & 0xff];
                    t = s;
                    do {
                        s++;
                    } while ((s - t) < skip && s < end);
                }
            } else {
                while (s < end) {
                    p = se = s + tlen1;
                    int t = tail;
                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return s;
                        }
                        p--; t--;
                    }
                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    int skip = regex.intMap[text[USE_SUNDAY_QUICK_SEARCH ? se + 1 : se] & 0xff];
                    t = s;
                    do {
                        s++;
                    } while ((s - t) < skip && s < end);

                }
            }
            return -1;
        }
    };

    static final Forward BM_NOT_REV_IC_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "EXACT_BM_NOT_REV_IC_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            char[]target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int tail = targetEnd - 1;
            int tlen1 = tail - targetP;
            int end = textRange;

            if (end + tlen1 > textEnd) {
                end = textEnd - tlen1;
            }
            int s = textP;

            if (Config.USE_BYTE_MAP || regex.intMap == null) {
                while (s < end) {
                    int se = s + tlen1;
                    if (lowerCaseMatch(target, targetP, targetEnd, text, s, se + 1)) {
                        return s;
                    }
                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    int skip = regex.map[text[USE_SUNDAY_QUICK_SEARCH ? se + 1 : se] & 0xff];
                    int t = s;
                    do {
                        s++;
                    } while ((s - t) < skip && s < end);
                }
            } else {
                while (s < end) {
                    int se = s + tlen1;
                    if (lowerCaseMatch(target, targetP, targetEnd, text, s, se + 1)) {
                        return s;
                    }
                    if (USE_SUNDAY_QUICK_SEARCH && (s + 1 >= end)) {
                        break;
                    }
                    int skip = regex.intMap[text[USE_SUNDAY_QUICK_SEARCH ? se + 1 : se] & 0xff];
                    int t = s;
                    do {
                        s++;
                    } while ((s - t) < skip && s < end);
                }
            }
            return -1;
        }
    };

    static final Forward MAP_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "MAP_FORWARD";
        }

        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            byte[]map = regex.map;
            int s = textP;

            while (s < textRange) {
                if (map[text[s] & 0xff] != 0) {
                    return s;
                }
                s++;
            }
            return -1;
        }
    };

    static final Backward MAP_BACKWARD = new Backward() {
        @Override
        final int search(Regex regex, char[]text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            final byte[]map = regex.map;
            int s = textStart;

            if (s >= textEnd) {
                s = textEnd - 1; // multibyte safe ?
            }
            while (s >= textP) {
                if (map[text[s] & 0xff] != 0) {
                    return s;
                }
                s = EncodingHelper.prevCharHead(adjustText, s);
            }
            return -1;
        }
    };

    static final Forward MAP_SB_FORWARD = new Forward() {
        @Override
        final String getName() {
            return "MAP_SB_FORWARD";
        }
        @Override
        final int search(Regex regex, char[]text, int textP, int textEnd, int textRange) {
            final byte[]map = regex.map;
            int s = textP;

            while (s < textRange) {
                if (map[text[s] & 0xff] != 0) {
                    return s;
                }
                s++;
            }
            return -1;
        }
    };

    static final Backward MAP_SB_BACKWARD = new Backward() {
        @Override
        final int search(Regex regex, char[]text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            final byte[]map = regex.map;
            int s = textStart;

            if (s >= textEnd) {
                s = textEnd - 1;
            }
            while (s >= textP) {
                if (map[text[s] & 0xff] != 0) {
                    return s;
                }
                s--;
            }
            return -1;
        }
    };
}
