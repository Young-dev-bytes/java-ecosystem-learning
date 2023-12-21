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

package apple.laf;

import apple.laf.JRSUIConstants.*;
import apple.laf.JRSUIState.*;

public class JRSUIStateFactory {
    public static JRSUIState getSliderTrack() {
        return new JRSUIState(Widget.SLIDER.apply(NoIndicator.YES.apply(0)));
    }

    public static JRSUIState getSliderThumb() {
        return new JRSUIState(Widget.SLIDER_THUMB.apply(0));
    }

    public static JRSUIState getSpinnerArrows() {
        return new JRSUIState(Widget.BUTTON_LITTLE_ARROWS.apply(0));
    }

    public static JRSUIState getSplitPaneDivider() {
        return new JRSUIState(Widget.DIVIDER_SPLITTER.apply(0));
    }

    public static JRSUIState getTab() {
        return new JRSUIState(Widget.TAB.apply(SegmentTrailingSeparator.YES.apply(0)));
    }

    public static AnimationFrameState getDisclosureTriangle() {
        return new AnimationFrameState(Widget.DISCLOSURE_TRIANGLE.apply(0), 0);
    }

    public static ScrollBarState getScrollBar() {
        return new ScrollBarState(Widget.SCROLL_BAR.apply(0), 0, 0, 0);
    }

    public static TitleBarHeightState getTitleBar() {
        return new TitleBarHeightState(Widget.WINDOW_FRAME.apply(0), 0);
    }

    public static ValueState getProgressBar() {
        return new ValueState(0, 0);
    }

    public static ValueState getLabeledButton() {
        return new ValueState(0, 0);
    }
}
