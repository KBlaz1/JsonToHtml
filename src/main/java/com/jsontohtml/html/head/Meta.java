package com.jsontohtml.html.head;

import com.google.gson.JsonElement;
import com.jsontohtml.html.HtmlElement;

import java.util.Map;

public class Meta extends HtmlElement {

    public static Meta meta_from_json(Map.Entry<String, JsonElement> attributeMap) {
        Meta meta  = new Meta();
        meta.setName(attributeMap.getKey());
        if (attributeMap.getValue().isJsonPrimitive())
            meta.setContent(attributeMap.getValue().getAsString());
        else
            attributeMap.getValue().getAsJsonObject().entrySet().forEach(value -> {
                if (meta.getContent() == null)
                    meta.setContent(value.toString().replace("\"", ""));
                else
                    meta.setContent(meta.getContent() + ", " + value.toString().replace("\"", ""));
            });
        return meta;
    }

    @Override
    public String toString(Integer spaces) {
        StringBuilder meta = new StringBuilder(String.format("%s<meta", " ".repeat(spaces)));

        if (getName().equals("charset"))
            meta.append(String.format(" %s=%s", getName(), "\"" + getContent() + "\""));
        else
            meta.append(String.format(" name=%s", "\"" + getName() + "\"")).append(String.format(" content=%s", "\"" + getContent() + "\""));
        meta.append(">");
        return meta.toString();
    }
}
