package com.vaibhav.rabbit_spring.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.exchange.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.json.queue.name}")
    private String jsonQueueName;

    @Value("${spring.rabbitmq.exchange.json.routing-key}")
    private String jsonRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
   public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName, false);
    }

    @Bean
    public Binding jsonBinding(Queue jsonQueue, TopicExchange exchange) {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    //connection factory
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("springboot");
        return connectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



    //rabbit template
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
       RabbitTemplate jsonRabbitTemplate = new RabbitTemplate(connectionFactory());
        jsonRabbitTemplate.setMessageConverter(jsonMessageConverter());
        return jsonRabbitTemplate;
    }


}
