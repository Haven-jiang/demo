package com.Haven.consumer.fanout;

import com.Haven.dto.EmailDTO;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.Haven.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static com.Haven.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * 邮件 消费者
 *
 * @author HavenJust
 * @date 2022/4/8
 */
@Component
@RabbitListener(bindings =@QueueBinding(
        // email.fanout.queue 是队列名字，这个名字你可以自定随便定义。
        value = @Queue(value = EMAIL_QUEUE, durable = "true", autoDelete = "false"),
        // order.fanout 交换机的名字 必须和生产者保持一致
        exchange = @Exchange(value = EMAIL_EXCHANGE,
                // 这里是确定的rabbitmq模式是：fanout 是以广播模式 、 发布订阅模式
                type = ExchangeTypes.FANOUT)
))
public class EmailConsumer {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitHandler
    public void reviseMessage(byte[] data) {
        EmailDTO emailDTO = JSON.parseObject(new String(data), EmailDTO.class);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(emailDTO.getReceivers());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getContent());
        javaMailSender.send(message);
    }
}
