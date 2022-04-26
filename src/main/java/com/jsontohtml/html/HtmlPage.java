package com.jsontohtml.html;

import com.google.gson.JsonObject;
import com.jsontohtml.html.body.BodyElement;
import com.jsontohtml.html.head.HeadElement;

public class HtmlPage {

    private String doctype;

    private String language;

    private HeadElement head;

    private BodyElement body;

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public HtmlElement getHead() {
        return head;
    }

    public void setHead(HeadElement head) {
        this.head = head;
    }

    public BodyElement getBody() {
        return body;
    }

    public void setBody(BodyElement body) {
        this.body = body;
    }

    public static HtmlPage JsonObject_to_HtmlPage(JsonObject jsonObject) {
        HtmlPage htmlPage = new HtmlPage();

        htmlPage.doctype  = jsonObject.has("doctype") ? jsonObject.get("doctype").getAsString() : null;
        htmlPage.language = jsonObject.has("language") ? jsonObject.get("language").getAsString() : null;
        htmlPage.head     = HeadElement.headElement_from_json("head", jsonObject.get("head").getAsJsonObject().entrySet());

        // checking if body does not have any nested elements
        if (jsonObject.get("body").isJsonPrimitive())
            htmlPage.body = BodyElement.bodyElement_from_json("body", jsonObject.get("body").getAsString());
        else
            htmlPage.body = BodyElement.bodyElement_from_json("body", jsonObject.getAsJsonObject("body").entrySet());

        return htmlPage;
    }

    @Override
    public String toString() {
        StringBuilder htmlPage = new StringBuilder();

        htmlPage.append(String.format("<!DOCTYPE %s>\n", doctype));
        htmlPage.append(language != null ? String.format("<html lang=\"%s\">\n", language) : htmlPage.append("<html>"));
        htmlPage.append(head.toString(2) + "\n");
        htmlPage.append(body.toString(2));
        htmlPage.append("\n</html>");
        return htmlPage.toString();
    }
}
