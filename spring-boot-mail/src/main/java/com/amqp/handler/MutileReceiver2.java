package com.amqp.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mutile")
public class MutileReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1 mutile 2  : " + hello);
    }

}