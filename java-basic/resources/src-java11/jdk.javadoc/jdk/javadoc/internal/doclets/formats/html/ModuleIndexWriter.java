/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.formats.html;

import jdk.javadoc.internal.doclets.formats.html.markup.Table;
import jdk.javadoc.internal.doclets.formats.html.markup.TableHeader;

import java.util.*;

import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;

import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTag;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.markup.StringContent;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;

/**
 * Generate the module index page "overview-summary.html" for the right-hand
 * frame.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Bhavesh Patel
 */
public class ModuleIndexWriter extends AbstractModuleIndexWriter {

    /**
     * Construct the ModuleIndexWriter.
     * @param configuration the configuration object
     * @param filename the name of the generated file
     */
    public ModuleIndexWriter(HtmlConfiguration configuration, DocPath filename) {
        super(configuration, filename);
    }

    /**
     * Generate the module index page for the right-hand frame.
     *
     * @param configuration the current configuration of the doclet.
     * @throws DocFileIOException if there is a problem generating the module index page
     */
    public static void generate(HtmlConfiguration configuration) throws DocFileIOException {
        DocPath filename = DocPaths.overviewSummary(configuration.frames);
        ModuleIndexWriter mdlgen = new ModuleIndexWriter(configuration, filename);
        mdlgen.buildModuleIndexFile("doclet.Window_Overview_Summary", true);
    }

    /**
     * Add the module index.
     *
     * @param header the documentation tree to which the navigational links will be added
     * @param main the documentation tree to which the modules list will be added
     */
    @Override
    protected void addIndex(Content header, Content main) {
        addIndexContents(header, main);
    }

    /**
     * Adds module index contents.
     *
     * @param header the document tree to which the navigational links will be added
     * @param main the document tree to which the modules list will be added
     */
    protected void addIndexContents(Content header, Content main) {
        HtmlTree htmltree = (HtmlTree)createTagIfAllowed(HtmlTag.NAV, HtmlTree::NAV, () -> new HtmlTree(HtmlTag.DIV));
        htmltree.setStyle(HtmlStyle.indexNav);
        HtmlTree ul = new HtmlTree(HtmlTag.UL);
        addAllClassesLink(ul);
        if (configuration.showModules) {
            addAllModulesLink(ul);
        }
        htmltree.addContent(ul);
        header.addContent(htmltree);
        addModulesList(main);
    }

    /**
     * Add the list of modules.
     *
     * @param main the content tree to which the module list will be added
     */
    @Override
    protected void addModulesList(Content main) {
        Map<String, SortedSet<ModuleElement>> groupModuleMap
                = configuration.group.groupModules(configuration.modules);

        if (!groupModuleMap.keySet().isEmpty()) {
            String tableSummary = configuration.getText("doclet.Member_Table_Summary",
                    configuration.getText("doclet.Module_Summary"), configuration.getText("doclet.modules"));
            TableHeader header = new TableHeader(contents.moduleLabel, contents.descriptionLabel);
            Table table =  new Table(configuration.htmlVersion, HtmlStyle.overviewSummary)
                    .setSummary(tableSummary)
                    .setHeader(header)
                    .setColumnStyles(HtmlStyle.colFirst, HtmlStyle.colLast)
                    .setDefaultTab(resources.getText("doclet.All_Modules"))
                    .setTabScript(i -> "show(" + i + ");")
                    .setTabId(i -> (i == 0) ? "t0" : ("t" + (1 << (i - 1))));

            // add the tabs in command-line order
            for (String groupName : configuration.group.getGroupList()) {
                Set<ModuleElement> groupModules = groupModuleMap.get(groupName);
                if (groupModules != null) {
                    table.addTab(groupName, groupModules::contains);
                }
            }

            for (ModuleElement mdle : configuration.modules) {
                if (!mdle.isUnnamed()) {
                    if (!(configuration.nodeprecated && utils.isDeprecated(mdle))) {
                        Content moduleLinkContent = getModuleLink(mdle, new StringContent(mdle.getQualifiedName().toString()));
                        Content summaryContent = new ContentBuilder();
                        addSummaryComment(mdle, summaryContent);
                        table.addRow(mdle, moduleLinkContent, summaryContent);
                    }
                }
            }

            Content div = HtmlTree.DIV(HtmlStyle.contentContainer, table.toContent());
            main.addContent(div);

            if (table.needsScript()) {
                mainBodyScript.append(table.getScript());
            }
        }
    }

    /**
     * Adds the overview summary comment for this documentation. Add one line
     * summary at the top of the page and generate a link to the description,
     * which is added at the end of this page.
     *
     * @param main the documentation tree to which the overview header will be added
     */
    @Override
    protected void addOverviewHeader(Content main) {
        addConfigurationTitle(main);
        if (!utils.getFullBody(configuration.overviewElement).isEmpty()) {
            HtmlTree div = new HtmlTree(HtmlTag.DIV);
            div.setStyle(HtmlStyle.contentContainer);
            addOverviewComment(div);
            main.addContent(div);
        }
    }

    /**
     * Adds the overview comment as provided in the file specified by the
     * "-overview" option on the command line.
     *
     * @param htmltree the documentation tree to which the overview comment will
     *                 be added
     */
    protected void addOverviewComment(Content htmltree) {
        if (!utils.getFullBody(configuration.overviewElement).isEmpty()) {
            addInlineComment(configuration.overviewElement, htmltree);
        }
    }

    /**
     * Adds the top text (from the -top option), the upper
     * navigation bar, and then the title (from the"-title"
     * option), at the top of page.
     *
     * @param header the documentation tree to which the navigation bar header will be added
     */
    @Override
    protected void addNavigationBarHeader(Content header) {
        addTop(header);
        navBar.setUserHeader(getUserHeaderFooter(true));
        header.addContent(navBar.getContent(true));
    }

    /**
     * Adds the lower navigation bar and the bottom text
     * (from the -bottom option) at the bottom of page.
     *
     * @param footer the documentation tree to which the navigation bar footer will be added
     */
    @Override
    protected void addNavigationBarFooter(Content footer) {
        navBar.setUserFooter(getUserHeaderFooter(false));
        footer.addContent(navBar.getContent(false));
        addBottom(footer);
    }

    @Override
    protected void addModulePackagesList(Map<ModuleElement, Set<PackageElement>> modules, String text,
            String tableSummary, Content main, ModuleElement mdle) {
    }
}
