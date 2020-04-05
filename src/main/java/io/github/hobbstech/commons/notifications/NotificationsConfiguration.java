package io.github.hobbstech.commons.notifications;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.github.hobbstech.commons.notifications")
@EnableFeignClients(basePackages = "io.github.hobbstech.commons.notifications")
public class NotificationsConfiguration {
}
