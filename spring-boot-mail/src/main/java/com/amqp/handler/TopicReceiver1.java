package com.amqp.handler;


import com.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.TOPIC_MESSAGE)
public class TopicReceiver1 {

    @RabbitHandler
    public void process(String text) {
        System.out.println("Receiver1 message 1 : " + text);
    }

}