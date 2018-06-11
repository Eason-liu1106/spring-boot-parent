package com.amqp.handler;


import com.mail.model.MailDo;
import com.mail.service.MailSendService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "email")
public class EmailReceiver {
    @Autowired
    MailSendService mailSendService;
    @RabbitHandler
    public void process(MailDo mailDo) {

    mailSendService.sendAttachmentsMail(mailDo);

    }

}