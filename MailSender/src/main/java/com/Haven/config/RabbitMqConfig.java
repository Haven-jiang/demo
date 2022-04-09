package com.Haven.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.Haven.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static com.Haven.constant.MQPrefixConst.EMAIL_QUEUE;

@Configuration
public class RabbitMqConfig {


    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(EMAIL_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingEmailDirect() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with("#.email.#");
    }


    @Bean
    public Queue smsQueue() {
        return new Queue("sms.direct.queue", true);
    }

    @Bean
    public DirectExchange smsExchange() {
        return new DirectExchange("direct_sms_exchange", true, false);
    }

    @Bean
    public Binding bindingSmsDirect() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with("#.sms.#");
    }
}