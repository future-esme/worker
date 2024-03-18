package utm.md.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import utm.md.service.SendTelegramNotificationService;
import utm.md.service.dto.NotificationDTO;

import static utm.md.config.rabbitmq.RabbitMqConstants.TELEGRAM_QUEUE;

@Profile({"telegram"})
@Component
@EnableRabbit
public class TelegramListener {

    private final Logger log = LoggerFactory.getLogger(TelegramListener.class);

    private final SendTelegramNotificationService telegramNotificationService;

    public TelegramListener(SendTelegramNotificationService telegramNotificationService) {
        this.telegramNotificationService = telegramNotificationService;
    }


    @RabbitListener(queues = TELEGRAM_QUEUE)
    public void receiveMessage(NotificationDTO notification) {
        log.info("Receive telegramEvent event ");
        telegramNotificationService.sendNotification(notification);
    }
}
