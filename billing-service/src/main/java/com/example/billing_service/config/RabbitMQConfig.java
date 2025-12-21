package com.example.billing_service.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "notification_queue";
    public static final String EXCHANGE = "sritel_exchange";
    public static final String ROUTING_KEY = "bill.paid";

    // 1. Define the Queue (Mailbox)
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    // 2. Define the Exchange (Post Office Hub)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // 3. Link Queue to Exchange with a Routing Key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}