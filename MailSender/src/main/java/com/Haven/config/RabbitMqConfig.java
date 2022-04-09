package com.Haven.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.Haven.constant.MQPrefixConst.SEND_EXCHANGE;
import static com.Haven.constant.MQPrefixConst.EMAIL_QUEUE;

@Configuration
public class RabbitMqConfig {


    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public DirectExchange sendExchange() {
        return new DirectExchange(SEND_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingEmailDirect() {
        return BindingBuilder.bind(emailQueue()).to(sendExchange()).with("email");
    }


    @Bean
    public Queue smsQueue() {
        return new Queue("sms.direct.queue", true);
    }
    @Bean
    public Binding bindingSmsDirect() {
        return BindingBuilder.bind(smsQueue()).to(sendExchange()).with("#.sms.#");
    }
}