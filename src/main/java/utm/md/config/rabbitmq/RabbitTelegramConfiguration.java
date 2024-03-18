package utm.md.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static utm.md.config.rabbitmq.RabbitMqConstants.*;

@Profile("telegram")
@Configuration
public class RabbitTelegramConfiguration {
    @Bean
    public Queue telegramQueue() {
        return new Queue(TELEGRAM_QUEUE);
    }

    @Bean
    DirectExchange telegramExchange() {
        return new DirectExchange(TELEGRAM_EXCHANGE, true, false);
    }


    @Bean
    public Binding telegramBinding() {
        return BindingBuilder.bind(telegramQueue()).to(telegramExchange()).with(TELEGRAM_ROUTING_KEY);
    }
}
