package com.Haven.service.comsumer;

import com.Haven.dto.EmailDTO;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.Haven.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * 邮件 消费者
 *
 * @author HavenJust
 * @date 2022/4/8
 */
@Component
@RabbitListener(queues = {EMAIL_QUEUE})
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
        System.out.println("已发送成功");
    }
}
