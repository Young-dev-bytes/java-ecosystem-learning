/*
 * Copyright (c) 2012, 2017, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Classes for receiving gesture events. Provides a mechanism to receive various
 * gesture events on JComponents. Gesture notifications are relayed up the
 * component hierarchy from the deepest component under the cursor to the
 * top-level container. Events may be consumed by deeper components to prevent
 * them from propagating to higher components. Gesture listeners are added to
 * components using the GestureUtilities helper class.
 */
package com.apple.eawt.event;
