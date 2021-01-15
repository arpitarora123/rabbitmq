package com.example.demo;

import com.example.demo.listener.AMQPListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        cachingConnectionFactory.setHost("localhost");
        cachingConnectionFactory.setPort(5672);
//        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
//        cachingConnectionFactory.setChannelCheckoutTimeout(1000);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("TestExchange");
        rabbitTemplate.setRoutingKey("TestKey");
        rabbitTemplate.setDefaultReceiveQueue("test");
        return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("test")
                .build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange("TestExchange")
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with("TestKey")
                .noargs();
    }

//    @Bean
//    public AMQPListener receiver1() {
//        return new AMQPListener(1);
//    }
}
