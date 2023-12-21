/*
 * Copyright (c) 2004, 2010, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvmstat.perfdata.monitor;

import sun.jvmstat.monitor.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Class for monitoring a PerfData String instrument.
 *
 * @author Brian Doherty
 * @since 1.5
 */
public class PerfStringMonitor extends PerfByteArrayMonitor
       implements StringMonitor {

    private static Charset defaultCharset = Charset.defaultCharset();

    /**
     * Constructor to create a StringMonitor object for the string instrument
     * represented by the data in the given buffer.
     *
     * @param name the name of the string instrument
     * @param v the variability attribute
     * @param supported support level indicator
     * @param bb the buffer containing the string instrument data.
     */
    public PerfStringMonitor(String name, Variability v, boolean supported,
                             ByteBuffer bb) {
        this(name, v, supported, bb, bb.limit());
    }

    /**
     * Constructor to create a StringMonitor object for the string instrument
     * represented by the data in the given buffer.
     *
     * @param name the name of the string instrument
     * @param v the variability attribute
     * @param supported support level indicator
     * @param bb the buffer containing the string instrument data.
     * @param maxLength the maximum length of the string data.
     */
    public PerfStringMonitor(String name, Variability v, boolean supported,
                             ByteBuffer bb, int maxLength) {
        super(name, Units.STRING, v, supported, bb, maxLength);
    }

    /**
     * {@inheritDoc}
     * The object returned contains a String with a copy of the current
     * value of the StringInstrument.
     *
     * @return Object - a copy of the current value of the StringInstrument.
     *                  The return value is guaranteed to be of type String.
     */
    public Object getValue() {
        return stringValue();
    }

    /**
     * Return the current value of the StringInstrument as a String.
     *
     * @return String - a copy of the current value of the StringInstrument.
     */
    public String stringValue() {
        String str = "";
        byte[] b = byteArrayValue();

        // catch null strings
        if ((b == null) || (b.length <= 1) || (b[0] == (byte)0)) {
            return str;
        }

        int i;
        for (i = 0; i < b.length && b[i] != (byte)0; i++);

        return new String(b, 0, i, defaultCharset);
    }
}
