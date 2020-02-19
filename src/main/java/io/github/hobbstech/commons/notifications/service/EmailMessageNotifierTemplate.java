package io.github.hobbstech.commons.notifications.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Collection;
import java.util.HashSet;

@Slf4j
public abstract class EmailMessageNotifierTemplate {

    protected final Collection<EmailUser> recipients = new HashSet<>();
    protected final EmailMessageFormatter emailMessageFormatter = new EmailMessageFormatter();
    private final EmailSender emailSender;
    protected String subject;

    public EmailMessageNotifierTemplate(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    protected void sendEmail() {

        val message = emailMessageFormatter.buildMessage();

        recipients.parallelStream().forEach(user -> {
            val emailMessageTransporter = EmailMessageTransporter.builder()
                    .message(String.format(message, user.getUsername()))
                    .subject(subject)
                    .build();
            emailSender.sendEmail(user, emailMessageTransporter);
        });

        clearRecipients();
        emailMessageFormatter.clearFields();

    }

    private void clearRecipients() {
        recipients.clear();
    }


}
