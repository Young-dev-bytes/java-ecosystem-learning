/*
 * Copyright (c) 2011, 2018, Oracle and/or its affiliates. All rights reserved.
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

package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ModuleElement;
import static javax.lang.model.SourceVersion.*;


/**
 * A skeletal visitor of program elements with default behavior
 * appropriate for source versions {@link SourceVersion#RELEASE_9
 * RELEASE_9} through {@link SourceVersion#RELEASE_11 RELEASE_11}.
 *
 * <p> <b>WARNING:</b> The {@code ElementVisitor} interface
 * implemented by this class may have methods added to it in the
 * future to accommodate new, currently unknown, language structures
 * added to future versions of the Java&trade; programming language.
 * Therefore, methods whose names begin with {@code "visit"} may be
 * added to this class in the future; to avoid incompatibilities,
 * classes which extend this class should not declare any instance
 * methods with names beginning with {@code "visit"}.
 *
 * <p>When such a new visit method is added, the default
 * implementation in this class will be to call the {@link
 * #visitUnknown visitUnknown} method.  A new abstract element visitor
 * class will also be introduced to correspond to the new language
 * level; this visitor will have different default behavior for the
 * visit method in question.  When the new visitor is introduced, all
 * or portions of this visitor may be deprecated.
 *
 * @param <R> the return type of this visitor's methods.  Use {@link
 *            Void} for visitors that do not need to return results.
 * @param <P> the type of the additional parameter to this visitor's
 *            methods.  Use {@code Void} for visitors that do not need an
 *            additional parameter.
 *
 * @see AbstractElementVisitor6
 * @see AbstractElementVisitor7
 * @see AbstractElementVisitor8
 * @since 9
 * @spec JPMS
 */
@SupportedSourceVersion(RELEASE_11)
public abstract class AbstractElementVisitor9<R, P> extends AbstractElementVisitor8<R, P> {
    /**
     * Constructor for concrete subclasses to call.
     */
    protected AbstractElementVisitor9(){
        super();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec Visits a {@code ModuleElement} in a manner defined by a
     * subclass.
     *
     * @param t  {@inheritDoc}
     * @param p  {@inheritDoc}
     * @return   {@inheritDoc}
     */
    @Override
    public abstract R visitModule(ModuleElement t, P p);
}
