/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.consumer;

import java.time.DateTimeException;
import java.time.ZoneOffset;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import jdk.jfr.internal.consumer.ChunkHeader;

/**
 * Converts ticks to nanoseconds
 */
final class TimeConverter {
    private final long startTicks;
    private final long startNanos;
    private final double divisor;
    private final ZoneOffset zoneOffet;

    TimeConverter(ChunkHeader chunkHeader, int rawOffset) {
        this.startTicks = chunkHeader.getStartTicks();
        this.startNanos = chunkHeader.getStartNanos();
        this.divisor = chunkHeader.getTicksPerSecond() / 1000_000_000L;
        this.zoneOffet = zoneOfSet(rawOffset);
    }

    private ZoneOffset zoneOfSet(int rawOffset) {
        try {
            return ZoneOffset.ofTotalSeconds(rawOffset / 1000);
        } catch (DateTimeException dte) {
            Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Could not create ZoneOffset from raw offset " + rawOffset);
        }
        return ZoneOffset.UTC;
    }

    public long convertTimestamp(long ticks) {
        return startNanos + (long) ((ticks - startTicks) / divisor);
    }

    public long convertTimespan(long ticks) {
        return (long) (ticks / divisor);
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffet;
    }
}
