package io.github.hobbstech.commons.notifications.core;

import io.github.hobbstech.commons.notifications.dto.Attachment;
import io.github.hobbstech.commons.notifications.dto.Body;
import io.github.hobbstech.commons.notifications.dto.EmailRecipient;
import io.github.hobbstech.commons.notifications.dto.Subject;

import java.util.Set;

public interface EmailContext {

    Set<EmailRecipient> getEmailRecipients();

    Set<Attachment> getAttachments();

    String getFrom();

    Body getBody();

    Subject getSubject();


}
