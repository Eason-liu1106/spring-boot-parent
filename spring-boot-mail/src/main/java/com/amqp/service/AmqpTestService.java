package com.amqp.service;

import com.amqp.model.SenderDo;
import com.mail.model.MailDo;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/5/22 0022 16:28
 */
public interface AmqpTestService {
    public void sendSimpleMessage(SenderDo senderDo);
    public void sendOneToManyMessage(SenderDo senderDo);
    public void sendManyToManyMessage(SenderDo senderDo);
    public void topicExchangeToOne(SenderDo senderDo);
    public void topicExchangeToMany(SenderDo senderDo);
    public void fanoutExchange(SenderDo senderDo);
    public void sendEmail(MailDo mailDo);

}
