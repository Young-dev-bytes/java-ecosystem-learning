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

package jdk.management.jfr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

final class StreamManager {

    public static final long TIME_OUT = TimeUnit.MINUTES.toMillis(2);
    public static final int DEFAULT_BLOCK_SIZE = 50000;

    private static long idCounter = 0;

    private final Map<Long, Stream> streams = new HashMap<>();
    private Timer timer;

    public synchronized Stream getStream(long streamIdentifer) {
        Stream stream = streams.get(streamIdentifer);
        if (stream == null) {
            throw new IllegalArgumentException("Unknown stream identifier " + streamIdentifer);
        }
        return stream;
    }

    public synchronized Stream create(InputStream is, int blockSize) {
        idCounter++;
        Stream stream = new Stream(is, idCounter, blockSize);
        streams.put(stream.getId(), stream);

        scheduleAbort(stream, System.currentTimeMillis() + TIME_OUT);
        return stream;
    }

    public synchronized void destroy(Stream stream) {
        try {
            stream.close();
        } catch (IOException e) {
            // OK
        }
        streams.remove(stream.getId());
        if (streams.isEmpty()) {
            timer.cancel();
            timer = null;
        }
    }

    public synchronized void scheduleAbort(Stream s, long when) {
        if (timer == null) {
            timer = new Timer(true);
        }
        timer.schedule(new StreamCleanupTask(this, s), new Date(when + TIME_OUT));
    }
}
