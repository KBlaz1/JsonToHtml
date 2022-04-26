package com.jsontohtml.html.head;

import com.google.gson.JsonElement;
import com.jsontohtml.html.HtmlElement;
import com.jsontohtml.html.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Link extends HtmlElement {

    public static Link link_from_json(Set<Map.Entry<String, JsonElement>> htmlElementSet) {
        Link link                  = new Link();
        List<Attribute> attributes = new ArrayList<>();

        link.setName("link");
        htmlElementSet.forEach(value -> attributes.add(Attribute.attribute_from_json(value)));
        link.setAttributes(attributes);

        return link;
    }

    @Override
    public String toString(Integer spaces) {
        StringBuilder link = new StringBuilder(String.format("%s<%s ", " ".repeat(spaces), getName()));
        getAttributes().forEach(value -> {
            String divider = getAttributes().size()-1 != getAttributes().indexOf(value) ? " " : "";
            link.append(value.toString()).append(divider);
        });
        link.append(">");
        return link.toString();
    }
}
