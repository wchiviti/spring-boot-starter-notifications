package io.github.hobbstech.commons.notifications.dto;

import lombok.Data;

@Data
public final class EmailRecipient {

    private RecipientType type;

    private String emailAddress;

}
