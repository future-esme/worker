package utm.md.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utm.md.config.ApplicationProperties;
import utm.md.service.dto.NotificationDTO;
import utm.md.service.dto.NotificationShortDTO;
import utm.md.service.dto.telegram.TelegramDTO;

import static utm.md.util.RequestUtil.TELEGRAM_CHAT_ID;
import static utm.md.util.RequestUtil.TELEGRAM_MESSAGE_TEXT;

@Profile("telegram")
@Service
public class SendTelegramNotificationService {

    private final String token;
    private final String url;

    private final ApplicationProperties applicationProperties;

    public SendTelegramNotificationService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.token = applicationProperties.getTokens().getTelegram();
        this.url = applicationProperties.getApiUrl().getTelegram();
    }

    public void sendNotification(NotificationShortDTO notification) {
        var restTemplate = new RestTemplate();
        var address = url.formatted(token);
        var body = new TelegramDTO(notification.getReceiver(), notification.getContent());
        var response = restTemplate.postForEntity(address, body, String.class);
    }
}
