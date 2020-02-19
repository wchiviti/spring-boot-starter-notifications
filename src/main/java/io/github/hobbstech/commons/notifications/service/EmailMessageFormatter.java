package io.github.hobbstech.commons.notifications.service;

import lombok.*;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class EmailMessageFormatter {

    private static final String PARAGRAPH_BREAK = "<br /><br />";
    private static final String LINE_BREAK = "<br />";

    private String greeting;

    private List<String> paragraphs;

    private String salutation;

    private Collection<Link> links;

    public static String boldText(String text) {
        return "<strong>" + text + "</strong>";
    }

    public String buildMessage() {

        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append(isNull(greeting) ? "Hi " : greeting);
        bodyBuilder.append("%s");
        bodyBuilder.append(PARAGRAPH_BREAK);

        paragraphs.forEach(paragraph -> {
            bodyBuilder.append(paragraph);
            bodyBuilder.append(PARAGRAPH_BREAK);
        });

        if (nonNull(links) && !(links.isEmpty())) {

            bodyBuilder.append(boldText("Please click on the following links for : "));
            bodyBuilder.append(LINE_BREAK);
            links.forEach(link -> {
                bodyBuilder.append(getLinkText(link));
                bodyBuilder.append(LINE_BREAK);
            });

            bodyBuilder.append(PARAGRAPH_BREAK);

        }

        bodyBuilder.append(isNull(salutation) ? "Regards," : salutation);

        return bodyBuilder.toString();

    }

    void clearFields() {
        if (nonNull(links))
            links.clear();
        if (nonNull(paragraphs))
            paragraphs.clear();
    }

    private String getLinkText(Link link) {

        return boldText("<a href=" + link.getValue() + ">" + link.getTitle() + "</a>");

    }

    public void addGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void addParagraph(String paragraph) {
        if (isNull(this.paragraphs))
            this.paragraphs = new ArrayList<>();
        this.paragraphs.add(paragraph);
    }

    public void addSalutation(String salutation) {
        this.salutation = salutation;
    }

    public void addLink(Link link) {
        if (isNull(this.links))
            this.links = new HashSet<>();
        this.links.add(link);
    }

    public void addTabularHierarchy(String tabularHeading, Map<String, String> content) {

        paragraphs.add(boldText(tabularHeading));

        val builder = new StringBuilder();

        content.forEach((key, value) -> builder.append(boldText(key)).append(" : ").append(value).append(LINE_BREAK));

        paragraphs.add(builder.toString());

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Link {

        private String title;

        private String value;

    }

}
