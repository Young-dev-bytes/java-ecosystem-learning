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

package javax.print.attribute;

import java.io.Serializable;

/**
 * Class {@code HashPrintRequestAttributeSet} inherits its implementation from
 * class {@link HashAttributeSet HashAttributeSet} and enforces the semantic
 * restrictions of interface
 * {@link PrintRequestAttributeSet PrintRequestAttributeSet}.
 *
 * @author Alan Kaminsky
 */
public class HashPrintRequestAttributeSet extends HashAttributeSet
    implements PrintRequestAttributeSet, Serializable {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    private static final long serialVersionUID = 2364756266107751933L;

    /**
     * Construct a new, empty print request attribute set.
     */
    public HashPrintRequestAttributeSet() {
        super (PrintRequestAttribute.class);
    }

    /**
     * Construct a new print request attribute set, initially populated with the
     * given value.
     *
     * @param  attribute attribute value to add to the set
     * @throws NullPointerException if {@code attribute} is {@code null}
     */
    public HashPrintRequestAttributeSet(PrintRequestAttribute attribute) {
        super (attribute, PrintRequestAttribute.class);
    }

    /**
     * Construct a new print request attribute set, initially populated with the
     * values from the given array. The new attribute set is populated by adding
     * the elements of {@code attributes} array to the set in sequence, starting
     * at index 0. Thus, later array elements may replace earlier array elements
     * if the array contains duplicate attribute values or attribute categories.
     *
     * @param  attributes array of attribute values to add to the set. If
     *         {@code null}, an empty attribute set is constructed.
     * @throws NullPointerException if any element of {@code attributes} is
     *         {@code null}
     */
    public HashPrintRequestAttributeSet(PrintRequestAttribute[] attributes) {
        super (attributes, PrintRequestAttribute.class);
    }

    /**
     * Construct a new attribute set, initially populated with the values from
     * the given set where the members of the attribute set are restricted to
     * the {@code (PrintRequestAttributeSe} interface.
     *
     * @param  attributes set of attribute values to initialise the set. If
     *         {@code null}, an empty attribute set is constructed.
     * @throws ClassCastException if any element of {@code attributes} is not an
     *         instance of {@code PrintRequestAttributeSe}
     */
    public HashPrintRequestAttributeSet(PrintRequestAttributeSet attributes)
    {
        super(attributes, PrintRequestAttribute.class);
    }
}
