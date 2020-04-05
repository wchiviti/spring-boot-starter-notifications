package io.github.hobbstech.commons.notifications.service;

import io.github.hobbstech.commons.notifications.dto.SendEmailRequest;
import io.github.hobbstech.commons.notifications.dto.SendEmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notifications-service", url = "${notifications-service.url}")
public interface NotificationService {

    @PostMapping("/v2/send-email")
    SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest);

}
