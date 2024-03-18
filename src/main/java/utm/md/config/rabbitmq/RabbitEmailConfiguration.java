package utm.md.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static utm.md.config.rabbitmq.RabbitMqConstants.*;

@Profile("mail")
@Configuration
public class RabbitEmailConfiguration {
    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE);
    }

    @Bean
    DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE, true, false);
    }


    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAIL_ROUTING_KEY);
    }
}
