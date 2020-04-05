package io.github.hobbstech.commons.notifications.dto;

import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Data
public class SendEmailRequest {

    private Body body;

    private Subject subject;

    private Set<EmailRecipient> emailRecipients;

    private Set<Attachment> attachments;

    private String from;

    public Set<EmailRecipient> getEmailRecipients() {
        if (isNull(emailRecipients))
            emailRecipients = new HashSet<>();
        return Collections.unmodifiableSet(emailRecipients);
    }

    public Set<Attachment> getAttachments() {
        if (isNull(attachments))
            attachments = new HashSet<>();
        requireNonNull(attachments, "Email attachments should not be null");
        return Collections.unmodifiableSet(attachments);
    }

    public void addAttachment(Attachment attachment) {
        if (isNull(this.attachments))
            this.attachments = new HashSet<>();
        this.attachments.add(attachment);
    }
}
