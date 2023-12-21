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

package com.apple.laf;

import java.awt.Font;

import apple.laf.JRSUIUtils;
import com.apple.laf.AquaUtils.RecyclableSingleton;

public abstract class AquaInternalFrameBorderMetrics {
    private static final boolean useLegacyBorderMetrics = JRSUIUtils.InternalFrame.shouldUseLegacyBorderMetrics();

    public Font font;
    public int titleBarHeight;
    public int leftSidePadding;
    public int buttonHeight;
    public int buttonWidth;
    public int buttonPadding;
    public int downShift;

    private AquaInternalFrameBorderMetrics() {
        initialize();
    }

    protected abstract void initialize();

    public static AquaInternalFrameBorderMetrics getMetrics(boolean isUtility) {
        if (useLegacyBorderMetrics) {
            return isUtility ? legacyUtilityMetrics.get() : legacyStandardMetrics.get();
        } else {
            return isUtility ? utilityMetrics.get() : standardMetrics.get();
        }
    }

    private static final RecyclableSingleton<AquaInternalFrameBorderMetrics> standardMetrics = new RecyclableSingleton<AquaInternalFrameBorderMetrics>() {
        @Override
        protected AquaInternalFrameBorderMetrics getInstance() {
            return new AquaInternalFrameBorderMetrics() {
                protected void initialize() {
                    font = new Font("Lucida Grande", Font.PLAIN, 13);
                    titleBarHeight = 22;
                    leftSidePadding = 7;
                    buttonHeight = 15;
                    buttonWidth = 15;
                    buttonPadding = 5;
                    downShift = 0;
                }
            };
        }
    };

    private static final RecyclableSingleton<AquaInternalFrameBorderMetrics> utilityMetrics = new RecyclableSingleton<AquaInternalFrameBorderMetrics>() {
        @Override
        protected AquaInternalFrameBorderMetrics getInstance() {
            return new AquaInternalFrameBorderMetrics() {
                protected void initialize() {
                    font = new Font("Lucida Grande", Font.PLAIN, 11);
                    titleBarHeight = 16;
                    leftSidePadding = 6;
                    buttonHeight = 12;
                    buttonWidth = 12;
                    buttonPadding = 6;
                    downShift = 0;
                }
            };
        }
    };

    private static final RecyclableSingleton<AquaInternalFrameBorderMetrics> legacyStandardMetrics = new RecyclableSingleton<AquaInternalFrameBorderMetrics>() {
        @Override
        protected AquaInternalFrameBorderMetrics getInstance() {
            return new AquaInternalFrameBorderMetrics() {
                protected void initialize() {
                    font = new Font("Lucida Grande", Font.PLAIN, 13);
                    titleBarHeight = 22;
                    leftSidePadding = 8;
                    buttonHeight = 15;
                    buttonWidth = 15;
                    buttonPadding = 6;
                    downShift = 1;
                }
            };
        }
    };

    private static final RecyclableSingleton<AquaInternalFrameBorderMetrics> legacyUtilityMetrics = new RecyclableSingleton<AquaInternalFrameBorderMetrics>() {
        @Override
        protected AquaInternalFrameBorderMetrics getInstance() {
            return new AquaInternalFrameBorderMetrics() {
                protected void initialize() {
                    font = new Font("Lucida Grande", Font.PLAIN, 11);
                    titleBarHeight = 16;
                    leftSidePadding = 5;
                    buttonHeight = 13;
                    buttonWidth = 13;
                    buttonPadding = 5;
                    downShift = 0;
                }
            };
        }
    };
}
