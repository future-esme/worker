package utm.md.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static utm.md.config.rabbitmq.RabbitMqConstants.*;

@Profile("viber")
@Configuration
public class RabbitViberConfiguration {
    @Bean
    public Queue viberQueue() {
        return new Queue(VIBER_QUEUE);
    }

    @Bean
    DirectExchange viberExchange() {
        return new DirectExchange(VIBER_EXCHANGE, true, false);
    }


    @Bean
    public Binding viberBinding() {
        return BindingBuilder.bind(viberQueue()).to(viberExchange()).with(VIBER_ROUTING_KEY);
    }
}
