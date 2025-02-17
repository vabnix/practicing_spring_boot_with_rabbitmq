package com.vaibhav.rabbit_spring.subscriber;

import com.vaibhav.rabbit_spring.dto.JsonMessage;
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

    @RabbitListener(queues = "${spring.rabbitmq.json.queue.name}")
    public void receiveJsonMessage(JsonMessage message) {
        try {
            Thread.sleep(5000);
            log.info("[Received JSON message]: " + message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
