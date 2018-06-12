package com.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public final  static  String TOPIC_MESSAGE="topic.message";
    public final  static  String TOPIC_MESSAGES="topic.messages";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }


    @Bean
    public Queue message(){
        return new Queue(RabbitConfig.TOPIC_MESSAGE);
    }
    @Bean
    public Queue messages(){
        return new Queue(RabbitConfig.TOPIC_MESSAGES);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }
    @Bean
    public Binding topicBindMessage(Queue message , TopicExchange exchange){
        return BindingBuilder.bind(message).to(exchange).with("topic.message");

    }
    @Bean
    public Binding topicBindMessages(Queue messages , TopicExchange exchange){
        return BindingBuilder.bind(messages).to(exchange).with("topic.#");
    }
    @Bean
    public Queue emailQueue() {
        return new Queue("email");
    }
    @Bean
    public Binding topicBindMessageAndEmail(Queue emailQueue , TopicExchange exchange){
        return BindingBuilder.bind(emailQueue).to(exchange).with("email");

    }
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue mutileQueue() {
        return new Queue("mutile");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return  new FanoutExchange("fan");
    }
    @Bean
    public Binding bindingFanoutA(Queue helloQueue , FanoutExchange fanoutExchange ){
        return  BindingBuilder.bind(helloQueue).to(fanoutExchange);
    }
    @Bean
    public Binding bindingFanoutB(Queue message , FanoutExchange fanoutExchange ){
        return  BindingBuilder.bind(message).to(fanoutExchange);
    }


}