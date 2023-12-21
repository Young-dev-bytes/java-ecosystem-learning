/*
 * Copyright (c) 2001, 2018, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.toolkit.taglets;

import java.util.EnumSet;
import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.internal.doclets.toolkit.Content;

import static com.sun.source.doctree.DocTree.Kind.DOC_ROOT;

/**
 * An inline Taglet representing {&#064;docRoot}.  This taglet is
 * used to get the relative path to the document's root output
 * directory.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Jamie Ho
 * @author Doug Kramer
 */

public class DocRootTaglet extends BaseTaglet {

    /**
     * Construct a new DocRootTaglet.
     */
    public DocRootTaglet() {
        super(DOC_ROOT.tagName, true, EnumSet.allOf(Site.class));
    }

    @Override
    public Content getTagletOutput(Element holder, DocTree tag, TagletWriter writer) {
        return writer.getDocRootOutput();
    }
}
