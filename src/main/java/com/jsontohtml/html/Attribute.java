package com.jsontohtml.html;

import com.google.gson.JsonElement;

import java.util.*;

public class Attribute {

    private String name;

    private List<String> attributeValues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<String> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public static Attribute attribute_from_json(Map.Entry<String, JsonElement> attributeMap) {
        Attribute attribute          = new Attribute();
        List<String> attributeValues = new ArrayList<>();

        attribute.name = attributeMap.getKey();
        if (attributeMap.getValue().isJsonPrimitive())
            attributeValues.add(attributeMap.getValue().getAsString());
        else {
            attributeMap.getValue().getAsJsonObject().entrySet().forEach(value -> attributeValues.add(value.toString().replace('=',':').replace("\"", "")));
        }
        attribute.attributeValues = attributeValues;

        return attribute;
    }
    @Override
    public String toString() {
        StringBuilder attributes = new StringBuilder(String.format("%s=\"", name));
        if (name.equals("style"))
            attributeValues.forEach(value -> attributes.append(value).append(";"));
        else {
            attributeValues.forEach(value -> {
                String divider = attributeValues.size()-1 == attributeValues.indexOf(value) ? "" : " ";
                attributes.append(value + divider);
            });
        }
        attributes.append("\"");
        return attributes.toString();
    }
}
