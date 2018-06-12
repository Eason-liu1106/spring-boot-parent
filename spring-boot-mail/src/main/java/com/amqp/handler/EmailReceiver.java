package com.amqp.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.model.MailDo;
import com.mail.service.MailSendService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "email" ,containerFactory ="rabbitListenerContainerFactory")
public class EmailReceiver {
    @Autowired
    MailSendService mailSendService;
    @RabbitHandler
    public void process(byte[] mail) throws IOException {
    //将json数据转成对象
    ObjectMapper mapper=new ObjectMapper();
        MailDo mailDo=mapper.readValue(mail,MailDo.class);
        mailSendService.sendAttachmentsMail(mailDo);

    }

}