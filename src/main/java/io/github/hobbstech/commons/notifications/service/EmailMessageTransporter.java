package io.github.hobbstech.commons.notifications.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailMessageTransporter {

    private String subject;

    private String message;

    public EmailMessageTransporter(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }
}
