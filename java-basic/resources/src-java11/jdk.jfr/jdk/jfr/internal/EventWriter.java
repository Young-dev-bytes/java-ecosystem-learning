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

package jdk.jfr.internal;

import jdk.internal.misc.Unsafe;
import jdk.jfr.internal.consumer.RecordingInput;

/**
 * Class must reside in a package with package restriction.
 *
 * Users should not have direct access to underlying memory.
 *
 */
public final class EventWriter {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private final static JVM jvm = JVM.getJVM();

    private long startPosition;
    private long startPositionAddress;
    private long currentPosition;
    private long maxPosition;
    private final long threadID;
    private PlatformEventType eventType;
    private int maxEventSize;
    private boolean started;
    private boolean valid;
    private boolean flushOnEnd;
    // set by the JVM, not private to avoid being optimized out
    boolean notified;

    public static EventWriter getEventWriter() {
        EventWriter ew = (EventWriter)JVM.getEventWriter();
        return ew != null ? ew : JVM.newEventWriter();
    }

    public void putBoolean(boolean i) {
        if (isValidForSize(Byte.BYTES)) {
            currentPosition += Bits.putBoolean(currentPosition, i);
        }
    }

    public void putByte(byte i) {
        if (isValidForSize(Byte.BYTES)) {
            unsafe.putByte(currentPosition, i);
            ++currentPosition;
        }
    }

    public void putChar(char v) {
        if (isValidForSize(Character.BYTES + 1)) {
            putUncheckedLong(v);
        }
    }

    private void putUncheckedChar(char v) {
        putUncheckedLong(v);
    }

    public void putShort(short v) {
        if (isValidForSize(Short.BYTES + 1)) {
            putUncheckedLong(v & 0xFFFF);
        }
    }

    public void putInt(int v) {
        if (isValidForSize(Integer.BYTES + 1)) {
            putUncheckedLong(v & 0x00000000ffffffffL);
        }
    }

    private void putUncheckedInt(int v) {
        putUncheckedLong(v & 0x00000000ffffffffL);
    }

    public void putFloat(float i) {
        if (isValidForSize(Float.BYTES)) {
            currentPosition += Bits.putFloat(currentPosition, i);
        }
    }

    public void putLong(long v) {
        if (isValidForSize(Long.BYTES + 1)) {
            putUncheckedLong(v);
        }
    }

    public void putDouble(double i) {
        if (isValidForSize(Double.BYTES)) {
            currentPosition += Bits.putDouble(currentPosition, i);
        }
    }

    public void putString(String s, StringPool pool) {
        if (s == null) {
            putByte(RecordingInput.STRING_ENCODING_NULL);
            return;
        }
        int length = s.length();
        if (length == 0) {
            putByte(RecordingInput.STRING_ENCODING_EMPTY_STRING);
            return;
        }
        if (length > StringPool.MIN_LIMIT && length < StringPool.MAX_LIMIT) {
            long l = StringPool.addString(s);
            if (l > 0) {
                putByte(RecordingInput.STRING_ENCODING_CONSTANT_POOL);
                putLong(l);
                return;
            }
        }
        putStringValue(s);
        return;
    }

    private void putStringValue(String s) {
        int length = s.length();
        if (isValidForSize(1 + 5 + 3 * length)) {
            putUncheckedByte(RecordingInput.STRING_ENCODING_CHAR_ARRAY); // 1 byte
            putUncheckedInt(length); // max 5 bytes
            for (int i = 0; i < length; i++) {
                putUncheckedChar(s.charAt(i)); // max 3 bytes
            }
        }
    }

    public void putEventThread() {
        putLong(threadID);
    }

    public void putThread(Thread athread) {
        if (athread == null) {
            putLong(0L);
        } else {
            putLong(jvm.getThreadId(athread));
        }
    }

    public void putClass(Class<?> aClass) {
        if (aClass == null) {
            putLong(0L);
        } else {
            putLong(JVM.getClassIdNonIntrinsic(aClass));
        }
    }

    public void putStackTrace() {
        if (eventType.getStackTraceEnabled()) {
            putLong(jvm.getStackTraceId(eventType.getStackTraceOffset()));
        } else {
            putLong(0L);
        }
    }

    private void reserveEventSizeField() {
        // move currentPosition Integer.Bytes offset from start position
        if (isValidForSize(Integer.BYTES)) {
            currentPosition += Integer.BYTES;
        }
    }

    private void reset() {
        currentPosition = startPosition;
        if (flushOnEnd) {
            flushOnEnd = flush();
        }
        valid = true;
        started = false;
    }

    private boolean isValidForSize(int requestedSize) {
        if (!valid) {
            return false;
        }
        if (currentPosition + requestedSize > maxPosition) {
            flushOnEnd = flush(usedSize(), requestedSize);
            // retry
            if (currentPosition + requestedSize > maxPosition) {
                Logger.log(LogTag.JFR_SYSTEM,
                           LogLevel.WARN, () ->
                               "Unable to commit. Requested size " + requestedSize + " too large");
                valid = false;
                return false;
            }
        }
        return true;
    }

    private boolean isNotified() {
        return notified;
    }

    private void resetNotified() {
        notified = false;
    }

    private void resetStringPool() {
        StringPool.reset();
    }

    private int usedSize() {
        return (int) (currentPosition - startPosition);
    }

    private boolean flush() {
        return flush(usedSize(), 0);
    }

    private boolean flush(int usedSize, int requestedSize) {
        return JVM.flush(this, usedSize, requestedSize);
    }

    public boolean beginEvent(PlatformEventType eventType) {
        if (started) {
            // recursive write attempt
            return false;
        }
        started = true;
        this.eventType = eventType;
        reserveEventSizeField();
        putLong(eventType.getId());
        return true;
    }

    public boolean endEvent() {
        if (!valid) {
            reset();
            return true;
        }
        final int eventSize = usedSize();
        if (eventSize > maxEventSize) {
            reset();
            return true;
        }
        Bits.putInt(startPosition, makePaddedInt(eventSize));
        if (isNotified()) {
            resetNotified();
            resetStringPool();
            reset();
            // returning false will trigger restart of the event write attempt
            return false;
        }
        startPosition = currentPosition;
        unsafe.putAddress(startPositionAddress, startPosition);
        // the event is now committed
        if (flushOnEnd) {
            flushOnEnd = flush();
        }
        started = false;
        return true;
    }

    private EventWriter(long startPos, long maxPos, long startPosAddress, long threadID, boolean valid) {
        startPosition = currentPosition = startPos;
        maxPosition = maxPos;
        startPositionAddress = startPosAddress;
        this.threadID = threadID;
        started = false;
        flushOnEnd = false;
        this.valid = valid;
        notified = false;
        // event may not exceed size for a padded integer
        maxEventSize = (1 << 28) -1;
    }

    private static int makePaddedInt(int v) {
        // bit  0-6 + pad => bit 24 - 31
        long b1 = (((v >>> 0) & 0x7F) | 0x80) << 24;

        // bit  7-13 + pad => bit 16 - 23
        long b2 = (((v >>> 7) & 0x7F) | 0x80) << 16;

        // bit 14-20 + pad => bit  8 - 15
        long b3 = (((v >>> 14) & 0x7F) | 0x80) << 8;

        // bit 21-28       => bit  0 -  7
        long b4 = (((v >>> 21) & 0x7F)) << 0;

        return (int) (b1 + b2 + b3 + b4);
    }

    private void putUncheckedLong(long v) {
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 0-6
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 0-6
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 7-13
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 7-13
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 14-20
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 14-20
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 21-27
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 21-27
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 28-34
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 28-34
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 35-41
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 35-41
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 42-48
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 42-48
        v >>>= 7;

        if ((v & ~0x7FL) == 0L) {
            putUncheckedByte((byte) v); // 49-55
            return;
        }
        putUncheckedByte((byte) (v | 0x80L)); // 49-55
        putUncheckedByte((byte) (v >>> 7)); // 56-63, last byte as is.
    }

    private void putUncheckedByte(byte i) {
        unsafe.putByte(currentPosition, i);
        ++currentPosition;
    }
}
