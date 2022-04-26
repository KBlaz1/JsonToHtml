package com.jsontohtml.html.body;

import com.google.gson.JsonElement;
import com.jsontohtml.html.Attribute;
import com.jsontohtml.html.HtmlElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BodyElement extends HtmlElement {

    public static BodyElement bodyElement_from_json(String name, Set<Map.Entry<String, JsonElement>> htmlElementSet) {
        BodyElement bodyElement              = new BodyElement();
        List<Attribute> attributes           = new ArrayList<>();
        List<HtmlElement> nestedHtmlElements = new ArrayList<>();
        bodyElement.setName(name);

        htmlElementSet.forEach( value -> {
            if (value.getKey().equals("attributes"))
                value.getValue().getAsJsonObject().entrySet().forEach(attribute -> attributes.add(Attribute.attribute_from_json(attribute)));
            else {
                BodyElement nestedBodyElement = new BodyElement();
                if (value.getValue().isJsonPrimitive())
                    nestedBodyElement = bodyElement_from_json(value.getKey(), value.getValue().getAsString());
                else
                    nestedBodyElement = bodyElement_from_json(value.getKey(), value.getValue().getAsJsonObject().entrySet());

                nestedHtmlElements.add(nestedBodyElement);
            }
        });
        bodyElement.setAttributes(attributes);
        bodyElement.setNestedElements(nestedHtmlElements);

        return bodyElement;
    }

    public static BodyElement bodyElement_from_json(String name, String content) {
        BodyElement bodyElement = new BodyElement();
        bodyElement.setName(name);
        bodyElement.setContent(content);
        return bodyElement;
    }

    public String toString(Integer spaces) {
        StringBuilder bodyElement = new StringBuilder(String.format(" ".repeat(spaces) + "<%s", getName()));

        if (getAttributes() != null)
            getAttributes().forEach(value -> bodyElement.append(" ").append(value.toString()));

        bodyElement.append(">");

        if (getContent() != null)
            bodyElement.append(getContent()).append(String.format("</%s>", getName()));

        else if (getNestedElements() != null) {
            getNestedElements().forEach(value -> bodyElement.append("\n").append(value.toString(spaces + 2)));
            bodyElement.append(String.format("\n%s</%s>", " ".repeat(spaces), getName()));
        }

        return bodyElement.toString();
    }
}
