package utm.md.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import utm.md.service.SendViberNotificationService;
import utm.md.service.dto.NotificationDTO;

import static utm.md.config.rabbitmq.RabbitMqConstants.TELEGRAM_QUEUE;

@Profile({"viber"})
@Component
@EnableRabbit
public class ViberListener {

    private final Logger log = LoggerFactory.getLogger(ViberListener.class);

    private final SendViberNotificationService viberNotificationService;

    public ViberListener(SendViberNotificationService viberNotificationService) {
        this.viberNotificationService = viberNotificationService;
    }


    @RabbitListener(queues = TELEGRAM_QUEUE)
    public void receiveMessage(NotificationDTO notification) {
        log.info("Receive telegramEvent event ");
        viberNotificationService.sendNotification(notification);
    }
}
