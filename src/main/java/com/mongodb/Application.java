package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author oleg.sukhov 01.06.15.
 */
public class Application {
    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(Application.class, "/");

        Spark.get("/home", (request, response) -> {
            Template template = config.getTemplate("home.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            data.put("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-YYY hh:mm:ss")));
            template.process(data, writer);
            return writer;
        });
    }
}
