package utm.md.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import utm.md.service.SendEmailNotificationService;
import utm.md.service.dto.NotificationDTO;

import static utm.md.config.rabbitmq.RabbitMqConstants.MAIL_QUEUE;

@Profile({"mail"})
@Component
@EnableRabbit
public class MailListener {

    private final Logger log = LoggerFactory.getLogger(MailListener.class);

    private final SendEmailNotificationService sendEmailNotificationService;

    public MailListener(SendEmailNotificationService sendEmailNotificationService) {
        this.sendEmailNotificationService = sendEmailNotificationService;
    }


    @RabbitListener(queues = MAIL_QUEUE)
    public void receiveMessage(NotificationDTO notification) {
        log.info("Receive telegramEvent event ");
        sendEmailNotificationService.sendNotification(notification);
    }
}
