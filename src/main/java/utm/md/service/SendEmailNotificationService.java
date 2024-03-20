package utm.md.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tech.jhipster.config.JHipsterProperties;
import utm.md.service.dto.NotificationDTO;
import utm.md.service.dto.NotificationSubjectDTO;

import java.nio.charset.StandardCharsets;

@Profile("mail")
@Service
public class SendEmailNotificationService {

    private final Logger log = LoggerFactory.getLogger(SendEmailNotificationService.class);

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    public SendEmailNotificationService(JHipsterProperties jHipsterProperties,
                                        JavaMailSender javaMailSender) {
        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(NotificationDTO notification) {
        var notificationSubject = (NotificationSubjectDTO) notification;
        log.debug("Send email to {}", notificationSubject.getReceiver());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            message.setTo(notificationSubject.getReceiver());
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(notificationSubject.getSubject());
            message.setText(notificationSubject.getContent(), true);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to recipient '{}'", notificationSubject.getReceiver());
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", notificationSubject.getReceiver(), e);
        }
    }
}
