package com.vaibhav.rabbit_spring.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Subscriber {


    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void receiveMessage(String message) {
        try {
            Thread.sleep(5000);
            log.info("[Received message]: " + message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //can we add delay in receiving the message
    // @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    // public void receiveMessage(String message) {
    //     log.info("[Received message]: " + message);
    //     try {
    //         Thread.sleep(5000);
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }



}
