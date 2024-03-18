package utm.md.service.dto.viber;

public record ViberNotificationDTO(String receiver, ViberMessageTypeEnum type, ViberSenderBotDTO sender, String text) {}
