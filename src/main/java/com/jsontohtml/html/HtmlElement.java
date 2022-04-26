package com.jsontohtml.html;

import java.util.*;

public class HtmlElement {

    private String name;

    private String content;

    private List<Attribute> attributes;

    private List<HtmlElement> nestedElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<HtmlElement> getNestedElements() {
        return nestedElements;
    }

    public void setNestedElements(List<HtmlElement> nestedElements) {
        this.nestedElements = nestedElements;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString(Integer spaces) {
        return " ".repeat(spaces);
    }

}
