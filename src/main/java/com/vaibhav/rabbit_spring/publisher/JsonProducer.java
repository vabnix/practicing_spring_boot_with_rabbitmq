package com.vaibhav.rabbit_spring.publisher;

import com.vaibhav.rabbit_spring.dto.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("customRabbitTemplate")
    private final RabbitTemplate rabbitTemplate;

    public JsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void jsonMessage(JsonMessage message) {
        log.info("[Sending JSON message] -> to the RabbitMQ Queue");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
