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
import javax.print.attribute.SetOfIntegerSyntax;
import javax.print.attribute.SupportedValuesAttribute;

/**
 * Class {@code JobImpressionsSupported} is a printing attribute class, a set of
 * integers, that gives the supported values for a
 * {@link JobImpressions JobImpressions} attribute. It is restricted to a single
 * contiguous range of integers; multiple non-overlapping ranges are not
 * allowed. This gives the lower and upper bounds of the total sizes of print
 * jobs in number of impressions that the printer will accept.
 * <p>
 * <b>IPP Compatibility:</b> The {@code JobImpressionsSupported} attribute's
 * canonical array form gives the lower and upper bound for the range of values
 * to be included in an IPP "job-impressions-supported" attribute. See class
 * {@link SetOfIntegerSyntax SetOfIntegerSyntax} for an explanation of canonical
 * array form. The category name returned by {@code getName()} gives the IPP
 * attribute name.
 *
 * @author Alan Kaminsky
 */
public final class JobImpressionsSupported extends SetOfIntegerSyntax
        implements SupportedValuesAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    private static final long serialVersionUID = -4887354803843173692L;

    /**
     * Construct a new job impressions supported attribute containing a single
     * range of integers. That is, only those values of {@code JobImpressions}
     * in the one range are supported.
     *
     * @param  lowerBound lower bound of the range
     * @param  upperBound upper bound of the range
     * @throws IllegalArgumentException if a {@code null} range is specified or
     *         if a {@code non-null} range is specified with {@code lowerBound}
     *         less than zero
     */
    public JobImpressionsSupported(int lowerBound, int upperBound) {
        super (lowerBound, upperBound);
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Null range specified");
        } else if (lowerBound < 0) {
            throw new IllegalArgumentException(
                                         "Job K octets value < 0 specified");
        }
    }

    /**
     * Returns whether this job impressions supported attribute is equivalent to
     * the passed in object. To be equivalent, all of the following conditions
     * must be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class
     *   {@code JobImpressionsSupported}.
     *   <li>This job impressions supported attribute's members and
     *   {@code object}'s members are the same.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this job
     *         impressions supported attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals (object) &&
                object instanceof JobImpressionsSupported);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code JobImpressionsSupported}, the category is class
     * {@code JobImpressionsSupported} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return JobImpressionsSupported.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code JobImpressionsSupported}, the category name is
     * {@code "job-impressions-supported"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "job-impressions-supported";
    }
}
