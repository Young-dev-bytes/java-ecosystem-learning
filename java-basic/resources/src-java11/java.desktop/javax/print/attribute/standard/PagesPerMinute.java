/*
 * Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.
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

package javax.print.attribute.standard;

import javax.print.attribute.Attribute;
import javax.print.attribute.IntegerSyntax;
import javax.print.attribute.PrintServiceAttribute;

/**
 * Class {@code PagesPerMinute} is an integer valued printing attribute that
 * indicates the nominal number of pages per minute to the nearest whole number
 * which may be generated by this printer (e.g., simplex, black-and-white). This
 * attribute is informative, not a service guarantee. Generally, it is the value
 * used in the marketing literature to describe the device. A value of 0
 * indicates a device that takes more than two minutes to process a page.
 * <p>
 * <b>IPP Compatibility:</b> The integer value gives the IPP integer value. The
 * category name returned by {@code getName()} gives the IPP attribute name.
 *
 * @author Alan Kaminsky
 */
public final class PagesPerMinute extends IntegerSyntax
        implements PrintServiceAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    private static final long serialVersionUID = -6366403993072862015L;

    /**
     * Construct a new pages per minute attribute with the given integer value.
     *
     * @param  value Integer value
     * @throws IllegalArgumentException if {@code value} is negative
     */
    public PagesPerMinute(int value) {
        super(value, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this pages per minute attribute is equivalent to the
     * passed in object. To be equivalent, all of the following conditions must
     * be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code PagesPerMinute}.
     *   <li>This pages per minute attribute's value and {@code object}'s value
     *   are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this pages per
     *         minute attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals (object) &&
                object instanceof PagesPerMinute);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PagesPerMinute}, the category is class
     * {@code PagesPerMinute} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PagesPerMinute.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PagesPerMinute}, the category name is
     * {@code "pages-per-minute"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "pages-per-minute";
    }
}
