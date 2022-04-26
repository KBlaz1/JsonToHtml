package com.jsontohtml;

import com.google.gson.*;
import com.jsontohtml.html.HtmlPage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Converter {

    public static void main(String[] args) throws IOException {

        String[] jsonFiles = {"helloWorld", "pageNotFound", "pageNotFoundV2"};

        for (String file : jsonFiles) {
            File input  = new File(file + ".json");
            File output = new File(file + ".html");

            FileReader reader = new FileReader(input);
            FileWriter writer = new FileWriter(output);

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            HtmlPage htmlPage     = HtmlPage.JsonObject_to_HtmlPage(jsonObject);

            writer.write(htmlPage.toString());
            writer.close();

            System.out.printf("generated: %s.html%n", file);
        }
    }

}
