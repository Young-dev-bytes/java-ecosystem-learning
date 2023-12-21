/*
 * Copyright (c) 1998, 2014, Oracle and/or its affiliates. All rights reserved.
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

package java.awt.print;

/**
 * The {@code PrinterException} class and its subclasses are used
 * to indicate that an exceptional condition has occurred in the print
 * system.
 */

public class PrinterException extends Exception {
    private static final long serialVersionUID = -3757589981158265819L;

    /**
     * Constructs a new {@code PrinterException} object
     * without a detail message.
     */
    public PrinterException() {

    }

    /**
     * Constructs a new {@code PrinterException} object
     * with the specified detail message.
     * @param msg the message to generate when a
     * {@code PrinterException} is thrown
     */
    public PrinterException(String msg) {
        super(msg);
    }
}
