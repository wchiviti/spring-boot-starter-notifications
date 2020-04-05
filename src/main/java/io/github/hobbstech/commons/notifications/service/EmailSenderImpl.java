package io.github.hobbstech.commons.notifications.service;

import io.github.hobbstech.commons.notifications.dto.*;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

import static java.util.stream.Collectors.toSet;

@Log4j2
@Component
public class EmailSenderImpl implements EmailSender {

    private final NotificationService notificationService;

    @Value("${emails.from.name}")
    private String emailFrom;

    public EmailSenderImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void sendEmail(Collection<EmailUser> users, EmailMessageTransporter emailMessageTransporter) {

        SendEmailRequest sendEmailRequest = new SendEmailRequest();

        val recipients = users.parallelStream()
                .map(user -> {
                    EmailRecipient emailRecipient = new EmailRecipient();
                    emailRecipient.setType(RecipientType.TO);
                    emailRecipient.setEmailAddress(user.getEmail());
                    return emailRecipient;
                }).collect(toSet());

        sendEmailRequest.setEmailRecipients(recipients);

        val body = new Body();
        body.setMessage(emailMessageTransporter.getMessage());
        sendEmailRequest.setBody(body);

        val subject = new Subject();
        subject.setValue(emailMessageTransporter.getSubject());
        sendEmailRequest.setSubject(subject);
        sendEmailRequest.setFrom(emailFrom);
        notificationService.sendEmail(sendEmailRequest);

        log.debug("### Email Sent");

    }

    @Override
    public void sendEmail(EmailUser user, EmailMessageTransporter emailMessageTransporter) {
        val users = new HashSet<EmailUser>();
        users.add(user);
        sendEmail(users, emailMessageTransporter);
    }
}
