package com.amqp.service.impl;

import com.amqp.model.SenderDo;
import com.amqp.service.AmqpService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.model.MailDo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/5/22 0022 16:29
 */
@Service
public class AmqpServiceImpl implements AmqpService {
    @Autowired
    public AmqpTemplate amqpTemplate;
    @Override
    public void sendSimpleMessage(SenderDo senderDo) {
        amqpTemplate.convertAndSend("hello",senderDo.getContext());

    }
    @Override
    public void sendOneToManyMessage(SenderDo senderDo) {
        amqpTemplate.convertAndSend("mutile",senderDo.getContext());

    }

    @Override
    public void topicExchangeToMany(SenderDo senderDo) {
        amqpTemplate.convertAndSend("exchange" , "topic.messages" , senderDo.getContext());

    }

    @Override
    public void fanoutExchange(SenderDo senderDo) {
        amqpTemplate.convertAndSend("fan" ,"" , senderDo.getContext());
    }


    @Override
    public void topicExchangeToOne(SenderDo senderDo) {
        amqpTemplate.convertAndSend("exchange" , "topic.message" , senderDo.getContext());
    }

    @Override
    public void sendManyToManyMessage(SenderDo senderDo) {

    }

    @Override
    public void sendEmail(MailDo mailDo) throws JsonProcessingException {
        //将Java对象匹配JSON结构
//        ObjectMapper mapper=new ObjectMapper();
//        String message=mapper.writeValueAsString(mailDo);
        amqpTemplate.convertAndSend("exchange" , "email" , mailDo);


    }

}
