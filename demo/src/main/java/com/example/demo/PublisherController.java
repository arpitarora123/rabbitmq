package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/publish")
    HttpStatus publishMessage(@RequestParam String message) {

        System.out.println("Message " + message);
        Message message1 = new Message(message.getBytes(), new MessageProperties());
//        rabbitTemplate.send(message1);
        rabbitTemplate.convertAndSend(message);

//        rabbitTemplate.convertAndSend("TestExchange", "TestKey", new com.example.demo.Message(message));
        return HttpStatus.OK;
    }
}
