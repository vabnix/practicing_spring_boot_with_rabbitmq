package com.vaibhav.rabbit_spring.controller;

import com.vaibhav.rabbit_spring.dto.JsonMessage;
import com.vaibhav.rabbit_spring.publisher.JsonProducer;
import com.vaibhav.rabbit_spring.publisher.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private final Producer producer;

    @Autowired
    private final JsonProducer jsonProducer;

    public MessageController(Producer producer, JsonProducer jsonProducer) {
        this.producer = producer;
        this.jsonProducer = jsonProducer;
    }

    @PostMapping
    @RequestMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent to the RabbitMQ Queue";
    }

    @PostMapping
    @RequestMapping("/json-send")
    public String jsonMessage(@RequestBody JsonMessage message) {
        jsonProducer.jsonMessage(message);
        return "Message sent to the RabbitMQ Queue";
    }
}
