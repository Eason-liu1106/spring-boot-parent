package com.amqp.web;

import com.amqp.model.SenderDo;
import com.amqp.service.AmqpService;
import com.amqp.service.AmqpTestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mail.model.MailDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/5/22  16:30
 */
@RestController
@RequestMapping(value = "api/amqp")
public class AmqpController {
    @Autowired
    private AmqpService amqpService;
@Autowired
private AmqpTestService amqpTestService;


    @RequestMapping("sendHello")
    public void sendHello(){
       //SenderDo senderDo = new SenderDo();
        //senderDo.setContext("hello,rabbitMQ"+ LocalDateTime.now());
//        amqpService.sendSimpleMessage(senderDo);
        MailDo mailDo = new MailDo();
        amqpTestService.sendEmail(mailDo);
    }
    @RequestMapping("mutileReciver")
    public void mutileReciver(){
        SenderDo senderDo = new SenderDo();
        for (int i = 0 ; i < 100 ; i++) {
            senderDo.setContext("hello,rabbitMQ" + i);
            amqpService.sendOneToManyMessage(senderDo);
        }
    }
    @RequestMapping("topicExchangeReciver")
    public void topicExchangeReciver(){
        SenderDo senderDo = new SenderDo();
        senderDo.setContext("这是指定给message队列的");
        amqpService.topicExchangeToOne(senderDo);
        senderDo.setContext("这是指定给messages_all队列的");
        amqpService.topicExchangeToMany(senderDo);
    }
    @RequestMapping("fanoutExchange")
    public void fanoutExchange(){
        SenderDo senderDo = new SenderDo();
        senderDo.setContext("这是一条广播");
        amqpService.fanoutExchange(senderDo);

    }
    @RequestMapping("mail")
    public void mail() throws JsonProcessingException {
        MailDo mailDo = new MailDo();
        mailDo.setFrom("liujialin1106@163.com");
        mailDo.setTo("liujialin1106@163.com");
        mailDo.setSubject("测试邮件");
        mailDo.setFilePath("D:\\info.txt");
        String text="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封HTML邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailDo.setText(text);
        amqpService.sendEmail(mailDo);

    }
}
