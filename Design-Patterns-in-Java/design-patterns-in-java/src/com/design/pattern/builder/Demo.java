package com.design.pattern.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HtmlElement {

    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();
    public String name, text;
    public List<HtmlElement> elements = new ArrayList<>();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {

        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));

        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement element : elements) {
            sb.append(element.toStringImpl(indent + 1));
        }

        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {

    private final String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public void addChild(String childName, String childText) {
        HtmlElement htmlElement = new HtmlElement(childName, childText);
        root.elements.add(htmlElement);

    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}


public class Demo {

    public static void main(String[] args) {
        /*String hello = "hello";
        System.out.println("<p>" + hello + "</p>");

        String[] words = {"hello", "world"};
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (String word : words) {
            sb.append(String.format(" <li>%s</li>\n", word));
        }
        sb.append("</ul>");

        System.out.println(sb);*/
        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "hello");
        builder.addChild("li", "world");
        System.out.println(builder);
    }
}
