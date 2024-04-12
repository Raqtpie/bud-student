package top.turingteam.budstudent.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static top.turingteam.budstudent.common.constant.MqCommon.*;

/**
 * @author Raqtpie
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue taskQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(taskQueue()).to(directExchange()).with(QUEUE_NAME);
    }
}
