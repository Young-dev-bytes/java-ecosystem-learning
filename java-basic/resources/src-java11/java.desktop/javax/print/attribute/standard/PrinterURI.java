/*
 * Copyright (c) 2001, 2017, Oracle and/or its affiliates. All rights reserved.
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

import java.net.URI;

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.URISyntax;

/**
 * Class {@code PrinterURI} is a printing attribute class, a {@code URI}, that
 * specifies the globally unique name of a printer. If it has such a name, an
 * administrator determines a printer's {@code URI} and sets this attribute to
 * that name.
 * <p>
 * <b>IPP Compatibility:</b> This implements the IPP printer-uri attribute. The
 * string form returned by {@code toString()} gives the IPP printer-uri value.
 * The category name returned by {@code getName()} gives the IPP attribute name.
 *
 * @author Robert Herriot
 */
public final class PrinterURI extends URISyntax
        implements PrintServiceAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    private static final long serialVersionUID = 7923912792485606497L;

    /**
     * Constructs a new {@code PrinterURI} attribute with the specified
     * {@code URI}.
     *
     * @param  uri {@code URI} of the printer
     * @throws NullPointerException if {@code uri} is {@code null}
     */
    public PrinterURI(URI uri) {
        super (uri);
    }

    /**
     * Returns whether this printer name attribute is equivalent to the passed
     * in object. To be equivalent, all of the following conditions must be
     * true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code PrinterURI}.
     *   <li>This {@code PrinterURI} attribute's underlying {@code URI} and
     *   {@code object}'s underlying {@code URI} are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this
     *         {@code PrinterURI} attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals(object) && object instanceof PrinterURI);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PrinterURI} and any vendor-defined subclasses, the
     * category is class {@code PrinterURI} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PrinterURI.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PrinterURI} and any vendor-defined subclasses, the
     * category name is {@code "printer-uri"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "printer-uri";
    }
}
