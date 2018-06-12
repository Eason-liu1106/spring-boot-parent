package com.amqp.service.impl;

import com.amqp.model.SenderDo;
import com.amqp.service.AmqpService;
import com.amqp.service.AmqpTestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mail.model.MailDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/6/7 0007 19:38
 */
@Service
public class AmqpTestServiceImpl implements AmqpTestService {
    @Autowired
    AmqpService amqpService;

    @Override
    public void sendSimpleMessage(SenderDo senderDo) throws JsonProcessingException {
        MailDo mailDo = new MailDo();
       amqpService.sendEmail(mailDo);
    }

    @Override
    public void sendOneToManyMessage(SenderDo senderDo) {

    }

    @Override
    public void sendManyToManyMessage(SenderDo senderDo) {

    }

    @Override
    public void topicExchangeToOne(SenderDo senderDo) {

    }

    @Override
    public void topicExchangeToMany(SenderDo senderDo) {

    }

    @Override
    public void fanoutExchange(SenderDo senderDo) {

    }

    @Override
    public void sendEmail(MailDo mailDo) {

    }
}
