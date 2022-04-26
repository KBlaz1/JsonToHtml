package com.jsontohtml.html.head;

import com.google.gson.JsonElement;
import com.jsontohtml.html.HtmlElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HeadElement extends HtmlElement {

    public static HeadElement headElement_from_json(String name, Set<Map.Entry<String, JsonElement>> htmlElementSet) {
        HeadElement headElement              = new HeadElement();
        List<HtmlElement> nestedHtmlElements = new ArrayList<>();

        headElement.setName(name);
        htmlElementSet.forEach(value -> {
            if (value.getKey().equals("meta"))
                value.getValue().getAsJsonObject().entrySet().forEach(meta -> nestedHtmlElements.add(Meta.meta_from_json(meta)));
            else if (value.getKey().equals("link"))
                value.getValue().getAsJsonArray().forEach(link -> nestedHtmlElements.add(Link.link_from_json(link.getAsJsonObject().entrySet())));
            else if (value.getValue().isJsonPrimitive()){
                nestedHtmlElements.add(headElement_from_json(value.getKey(), value.getValue().getAsString()));
            }
        });
        headElement.setNestedElements(nestedHtmlElements);

        return headElement;
    }

    public static HeadElement headElement_from_json(String name, String content) {
        HeadElement headElement = new HeadElement();
        headElement.setName(name);
        headElement.setContent(content);
        return headElement;
    }

    @Override
    public String toString(Integer spaces) {
        StringBuilder headElement = new StringBuilder(String.format("%s<%s>", " ".repeat(spaces), getName()));

        if (getContent() != null)
            headElement.append(getContent()).append(String.format("</%s>", getName()));
        else if (getNestedElements() != null) {
            getNestedElements().forEach(value -> headElement.append("\n").append(value.toString(spaces + 2)));
            headElement.append(String.format("\n%s</%s>", " ".repeat(spaces), getName()));
        }

        return headElement.toString();
    }
}
