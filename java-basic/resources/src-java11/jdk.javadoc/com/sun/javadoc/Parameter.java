/*
 * Copyright (c) 1998, 2018, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javadoc;

/**
 * Parameter information.
 * This includes a parameter type and parameter name.
 *
 * @author Robert Field
 *
 * @deprecated
 *   The declarations in this package have been superseded by those
 *   in the package {@code jdk.javadoc.doclet}.
 *   For more information, see the <i>Migration Guide</i> in the documentation for that package.
 */
@Deprecated(since="9", forRemoval=true)
@SuppressWarnings("removal")
public interface Parameter {

    /**
     * Get the type of this parameter.
     *
     * @return the type of this parameter.
     */
    Type type();

    /**
     * Get local name of this parameter.
     * For example if parameter is the short 'index', returns "index".
     *
     * @return the name of this parameter as a string.
     */
    String name();

    /**
     * Get type name of this parameter.
     * For example if parameter is the short 'index', returns "short".
     * <p>
     * This method returns a complete string
     * representation of the type, including the dimensions of arrays and
     * the type arguments of parameterized types.  Names are qualified.
     *
     * @return a complete string representation of the type.
     */
    String typeName();

    /**
     * Returns a string representation of the parameter.
     * <p>
     * For example if parameter is the short 'index', returns "short index".
     *
     * @return type and parameter name of this parameter.
     */
    String toString();

    /**
     * Get the annotations of this parameter.
     * Return an empty array if there are none.
     *
     * @return the annotations of this parameter.
     * @since 1.5
     */
    AnnotationDesc[] annotations();
}
