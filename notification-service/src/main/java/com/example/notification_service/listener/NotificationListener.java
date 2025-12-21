package com.example.notification_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {


    @RabbitListener(queues = "notification_queue")
    public void handleBillPaidEvent(String message) {

        System.out.println("------------------------------------------------");
        System.out.println("RECEIVED NOTIFICATION EVENT:");
        System.out.println("Email Sent to User. Content: " + message);
        System.out.println("------------------------------------------------");
    }
}