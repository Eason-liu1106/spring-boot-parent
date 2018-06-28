package com.amqp.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.model.MailDo;
import com.mail.service.MailSendService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "email" ,containerFactory ="rabbitListenerContainerFactory")
public class EmailReceiver {
    @Autowired
    MailSendService mailSendService;
    @RabbitHandler
    public void process(MailDo mailDo, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        System.out.println(mailDo);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //将json数据转成对象
//        ObjectMapper mapper=new ObjectMapper();
//        MailDo mailDo=mapper.readValue(mail,MailDo.class);
        mailSendService.sendAttachmentsMail(mailDo);
        try {
            // 确认消息
            channel.basicAck(tag,false);
            // 否认消息，重新入队
            //channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);
            //拒绝消息，消息被丢弃，不会入队
            //channel.basicReject((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}