package io.github.hobbstech.commons.notifications.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Service
public class EmailHtmlRepresentationBuilder {

    private final Configuration freeMarkerConfiguration;

    @Value("${system.name}")
    private String systemName;

    public EmailHtmlRepresentationBuilder(Configuration configuration) {
        this.freeMarkerConfiguration = configuration;
    }

    public String buildHtmlRepresentation(String message) {

        Map<String, Object> model = new ModelMap();

        model.put("message", message);
        model.put("systemName", systemName);
        model.put("year", String.valueOf(LocalDate.now().getYear()).replace(",", ""));

        String htmlString = "";

        try {

            Template t = freeMarkerConfiguration.getTemplate("email-template.ftl");

            htmlString = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        } catch (IOException | TemplateException e) {
            log.error("Failed to create an html representation of the email message due to : {}", e.getMessage());
        }

        return htmlString;

    }

}