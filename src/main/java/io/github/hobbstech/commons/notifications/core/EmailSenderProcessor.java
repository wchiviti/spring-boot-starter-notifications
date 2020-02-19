package io.github.hobbstech.commons.notifications.core;

@FunctionalInterface
public interface EmailSenderProcessor {

    void process(EmailContext emailContext);

}
