package com.vaibhav.rabbit_spring.publisher;

import com.vaibhav.rabbit_spring.dto.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonProducer {

    //this contains the logic to send the message to the queue
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.exchange.json.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.json.queue.name}")
    private String queueName;

    @Autowired
    private final RabbitTemplate jsonRabbitTemplate;

    public JsonProducer(RabbitTemplate jsonRabbitTemplate) {
        this.jsonRabbitTemplate = jsonRabbitTemplate;
    }

    public void jsonMessage(JsonMessage message) {
        log.info("[Sending message] -> to the RabbitMQ Queue");
        jsonRabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
