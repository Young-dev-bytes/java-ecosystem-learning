/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
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

package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/**
 * A class implementing the Closure interface that visits the nodes of
 * the nodes of a ColumFormat object and computes the header string for
 * the columns of data.
 *
 * @author Brian Doherty
 * @since 1.5
 */
public class HeaderClosure implements Closure {
    private static final char ALIGN_CHAR = '^';

    private StringBuilder header = new StringBuilder();

    /*
     * visit an object to perform some operation. In this case, the
     * object is a ColumnFormat we are building the header string.
     */
    public void visit(Object o, boolean hasNext) throws MonitorException {

        if (! (o instanceof ColumnFormat)) {
            return;
        }

        ColumnFormat c = (ColumnFormat)o;

        String h = c.getHeader();

        // check for special alignment character
        if (h.indexOf(ALIGN_CHAR) >= 0) {
            int len = h.length();
            if ((h.charAt(0) == ALIGN_CHAR)
                    && (h.charAt(len-1) == ALIGN_CHAR)) {
                // ^<header>^ case - center alignment
                c.setWidth(Math.max(c.getWidth(),
                                    Math.max(c.getFormat().length(), len-2)));
                h = h.substring(1, len-1);
                h = Alignment.CENTER.align(h, c.getWidth());
            } else if (h.charAt(0) == ALIGN_CHAR) {
                // ^<header> case - left alignment
                c.setWidth(Math.max(c.getWidth(),
                                    Math.max(c.getFormat().length(), len-1)));
                h = h.substring(1, len);
                h = Alignment.LEFT.align(h, c.getWidth());
            } else if (h.charAt(len-1) == ALIGN_CHAR) {
                // <header>^ case - right alignment
                c.setWidth(Math.max(c.getWidth(),
                           Math.max(c.getFormat().length(), len-1)));
                h = h.substring(0, len-1);
                h = Alignment.RIGHT.align(h, c.getWidth());
            } else {
                // an internal alignment character - ignore
            }
        } else {
            // User has provided their own padding for alignment purposes
        }

        header.append(h);
        if (hasNext) {
            header.append(" ");
        }
    }

    /*
     * get the header string.
     */
    public String getHeader() {
        return header.toString();
    }
}
