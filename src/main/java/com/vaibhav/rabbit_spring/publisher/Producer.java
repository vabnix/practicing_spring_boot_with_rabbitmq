package com.vaibhav.rabbit_spring.publisher;

import com.vaibhav.rabbit_spring.dto.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    //this contains the logic to send the message to the queue
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.exchange.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        log.info("[Sending message] -> to the RabbitMQ Queue");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
