package utm.md.service.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramDTO(@JsonProperty("chat_id") String chatId, @JsonProperty("text") String text) {
}
