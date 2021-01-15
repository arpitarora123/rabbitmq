package com.example.demo.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;

@Component
public class AMQPListener {

//    private final int instance;
//
//    public AMQPListener(int i) {
//        this.instance = i;
//    }

    @RabbitListener(queues = "test", ackMode = "MANUAL")
    public void receive(String in, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException, InterruptedException {

        System.out.println(in + " tag " + tag);
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + //this.instance +
                " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
//        channel.basicAck(tag, false);
//        channel.basicNack(tag, false, true);
        System.out.println("instance " + //this.instance +
                " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
//        throw new AmqpRejectAndDontRequeueException("Rejected Not re-publishing");
    }
}
