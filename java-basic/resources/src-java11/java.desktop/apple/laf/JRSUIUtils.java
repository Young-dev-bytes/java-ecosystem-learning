/*
 * Copyright (c) 2011, 2021, Oracle and/or its affiliates. All rights reserved.
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

package apple.laf;

import java.security.AccessController;

import apple.laf.JRSUIConstants.Hit;
import apple.laf.JRSUIConstants.ScrollBarPart;
import com.apple.laf.AquaImageFactory.NineSliceMetrics;
import sun.security.action.GetPropertyAction;

public final class JRSUIUtils {

    static boolean isLeopard = isMacOSXLeopard();
    static boolean isSnowLeopardOrBelow = isMacOSXSnowLeopardOrBelow();
    static boolean isCatalinaOrAbove = isMacOSXCatalinaOrAbove();

    public static boolean isMacOSXBigSurOrAbove() {
        return currentMacOSXVersionMatchesGivenVersionRange(10, 16, true,
                false, true);
    }

    static boolean isMacOSXCatalinaOrAbove() {
        return currentMacOSXVersionMatchesGivenVersionRange(10, 15, true,
                false, true);
    }

    static boolean isMacOSXLeopard() {
        return isCurrentMacOSXVersion(5);
    }

    static boolean isMacOSXSnowLeopardOrBelow() {
        return currentMacOSXVersionMatchesGivenVersionRange(10, 6, true,
                true, false);
    }

    static boolean isCurrentMacOSXVersion(final int version) {
        return isCurrentMacOSXVersion(10, version);
    }

    static boolean isCurrentMacOSXVersion(final int major, final int minor) {
        return currentMacOSXVersionMatchesGivenVersionRange(major, minor, true, false, false);
    }

    static boolean currentMacOSXVersionMatchesGivenVersionRange(
            final int version, final boolean inclusive,
            final boolean matchBelow, final boolean matchAbove) {
        return currentMacOSXVersionMatchesGivenVersionRange(10, version, inclusive, matchBelow, matchAbove);
    }

    static boolean currentMacOSXVersionMatchesGivenVersionRange(
            final int majorVersion, final int minorVersion, final boolean inclusive,
            final boolean matchBelow, final boolean matchAbove) {
        // split the "x.y.z" version number
        String osVersion = AccessController.doPrivileged(new GetPropertyAction("os.version"));
        String[] fragments = osVersion.split("\\.");

        if (fragments.length < 2) return false;

        // check if os.version matches the given version using the given match method
        try {
            int majorVers = Integer.parseInt(fragments[0]);
            int minorVers = Integer.parseInt(fragments[1]);

            if (inclusive && majorVers == majorVersion && minorVers == minorVersion) return true;
            if (matchBelow &&
                    (majorVers < majorVersion ||
                            (majorVers == majorVersion && minorVers < minorVersion))) return true;
            if (matchAbove &&
                    (majorVers > majorVersion ||
                            (majorVers == majorVersion && minorVers > minorVersion))) return true;

        } catch (NumberFormatException e) {
            // was not an integer
        }
        return false;
    }

    public static class TaskBar {
        public static boolean isIconBadgeSupported() {
            return !isCatalinaOrAbove;
        }
    }

    public static class TabbedPane {
        public static boolean useLegacyTabs() {
            return isLeopard;
        }
        public static boolean shouldUseTabbedPaneContrastUI() {
            return !isSnowLeopardOrBelow;
        }
    }

    public static class InternalFrame {
        public static boolean shouldUseLegacyBorderMetrics() {
            return isSnowLeopardOrBelow;
        }
    }

    public static class Tree {
        public static boolean useLegacyTreeKnobs() {
            return isLeopard;
        }
    }

    public static class ScrollBar {
        private static native boolean shouldUseScrollToClick();

        public static boolean useScrollToClick() {
            return shouldUseScrollToClick();
        }

        public static void getPartBounds(final double[] rect,
                                         final JRSUIControl control,
                                         final int x, final int y, final int w,
                                         final int h,
                                         final ScrollBarPart part) {
            control.getPartBounds(rect, x, y, w, h, part.ordinal);
        }

        public static double getNativeOffsetChange(final JRSUIControl control,
                                                   final int x, final int y,
                                                   final int w, final int h,
                                                   final int offset,
                                                   final int visibleAmount,
                                                   final int extent) {
            return control.getScrollBarOffsetChange(x, y, w, h, offset,
                                                    visibleAmount, extent);
        }
    }

    public static class Images {
        public static boolean shouldUseLegacySecurityUIPath() {
            return isSnowLeopardOrBelow;
        }
    }

    public static class HitDetection {
        public static Hit getHitForPoint(final JRSUIControl control,
                                         final int x, final int y, final int w,
                                         final int h, final int hitX,
                                         final int hitY) {
            return control.getHitForPoint(x, y, w, h, hitX, hitY);
        }
    }

    public interface NineSliceMetricsProvider {
        public NineSliceMetrics getNineSliceMetricsForState(JRSUIState state);
    }
}
