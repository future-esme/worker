package utm.md.service;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utm.md.config.ApplicationProperties;
import utm.md.service.dto.NotificationDTO;
import utm.md.service.dto.viber.ViberMessageTypeEnum;
import utm.md.service.dto.viber.ViberNotificationDTO;
import utm.md.service.dto.viber.ViberSenderBotDTO;
import utm.md.util.RequestUtil;

@Profile("viber")
@Service
public class SendViberNotificationService {

    private final String token;
    private final String url;
    private final ViberSenderBotDTO viberSenderBotDTO;
    private final RestTemplate restTemplate;

    private final ApplicationProperties applicationProperties;

    public SendViberNotificationService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.token = applicationProperties.getTokens().getViber();
        this.url = applicationProperties.getApiUrl().getViber();
        this.viberSenderBotDTO = new ViberSenderBotDTO(applicationProperties.getBotsData().getViber());
        this.restTemplate = RequestUtil.getRestTemplate();
    }

    public void sendNotification(NotificationDTO notification) {
        var notificationBody = new ViberNotificationDTO(notification.receiver(), ViberMessageTypeEnum.text, viberSenderBotDTO, notification.message());
        HttpEntity<ViberNotificationDTO> request = new HttpEntity<>(notificationBody, RequestUtil.getHttpHeaders(token));
        var response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }
}
