package utm.md.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Worker.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    @NotNull
    private Tokens tokens;

    @NotNull
    private Tokens apiUrl;

    @NotNull
    private Tokens botsData;

    public Tokens getTokens() {
        return tokens;
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    public Tokens getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(Tokens apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Tokens getBotsData() {
        return botsData;
    }

    public void setBotsData(Tokens botsData) {
        this.botsData = botsData;
    }

    public static class Tokens {

        @NotNull
        private String telegram;

        @NotNull
        private String viber;

        public String getTelegram() {
            return telegram;
        }

        public void setTelegram(String telegram) {
            this.telegram = telegram;
        }

        public String getViber() {
            return viber;
        }

        public void setViber(String viber) {
            this.viber = viber;
        }
    }
}
