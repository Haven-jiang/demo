package com.Haven.service.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = {"sms.direct.queue"})
@Component
public class SmsConsumer {

    @RabbitHandler
    public void send(String string) {
        System.out.println("已接受到------->" + string);
    }
}
