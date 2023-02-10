package io.essentials.adapter.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ViewTemplate {
    private String template;

    public static ViewTemplate create(String templateResource) {
        var resource = readResource(templateResource);
        return new ViewTemplate(resource);
    }

    public ViewTemplate(String template) {
        this.template = template;
    }

    public void replace(String tagName, String content) {
        template = template.replace("${" + tagName + "}", content);
    }

    public String getContent() {
        return template;
    }

    private static String readResource(String templateResource) {
        Class<ViewTemplate> clazz = ViewTemplate.class;
        InputStream inputStream = clazz.getResourceAsStream("/" + templateResource);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
            // TODO: 2/10/23 do this better.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultStringBuilder.toString();
    }
}
