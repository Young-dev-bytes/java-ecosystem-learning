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
import javax.print.attribute.DocAttribute;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.ResolutionSyntax;

/**
 * Class {@code PrinterResolution} is a printing attribute class that specifies
 * an exact resolution supported by a printer or to be used for a print job.
 * This attribute assumes that printers have a small set of device resolutions
 * at which they can operate rather than a continuum.
 * <p>
 * {@code PrinterResolution} is used in multiple ways:
 * <ol type=1>
 *   <li>When a client searches looking for a printer that supports the client's
 *   desired resolution exactly (no more, no less), the client specifies an
 *   instance of class {@code PrinterResolution} indicating the exact resolution
 *   the client wants. Only printers supporting that exact resolution will match
 *   the search.
 *   <li>When a client needs to print a job using the client's desired
 *   resolution exactly (no more, no less), the client specifies an instance of
 *   class {@code PrinterResolution} as an attribute of the Print Job. This will
 *   fail if the Print Job doesn't support that exact resolution, and
 *   {@code Fidelity} is set to true.
 * </ol>
 * If a client wants to locate a printer supporting a resolution greater than
 * some required minimum, then it may be necessary to exclude this attribute
 * from a lookup request and to directly query the set of supported resolutions,
 * and specify the one that most closely meets the client's requirements. In
 * some cases this may be more simply achieved by specifying a
 * {@code PrintQuality} attribute which often controls resolution.
 * <p>
 * <b>IPP Compatibility:</b> The information needed to construct an IPP
 * {@code "printer-resolution"} attribute can be obtained by calling methods on
 * the PrinterResolution object. The category name returned by {@code getName()}
 * gives the IPP attribute name.
 *
 * @author David Mendenhall
 * @author Alan Kaminsky
 */
public final class PrinterResolution    extends ResolutionSyntax
        implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    private static final long serialVersionUID = 13090306561090558L;

    /**
     * Construct a new printer resolution attribute from the given items.
     *
     * @param  crossFeedResolution cross feed direction resolution
     * @param  feedResolution feed direction resolution
     * @param  units unit conversion factor, e.g. {@code ResolutionSyntax.DPI}
     *         or {@code ResolutionSyntax.DPCM}
     * @throws IllegalArgumentException if {@code crossFeedResolution < 1} or
     *         {@code feedResolution < 1} or {@code units < 1}
     */
    public PrinterResolution(int crossFeedResolution, int feedResolution,
                             int units) {
        super (crossFeedResolution, feedResolution, units);
    }

    /**
     * Returns whether this printer resolution attribute is equivalent to the
     * passed in object. To be equivalent, all of the following conditions must
     * be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code PrinterResolution}.
     *   <li>This attribute's cross feed direction resolution is equal to
     *   {@code object}'s cross feed direction resolution.
     *   <li>This attribute's feed direction resolution is equal to
     *   {@code object}'s feed direction resolution.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this printer
     *         resolution attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals (object) &&
                object instanceof PrinterResolution);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PrinterResolution}, the category is class
     * {@code PrinterResolution} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PrinterResolution.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PrinterResolution}, the category name is
     * {@code "printer-resolution"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "printer-resolution";
    }
}
