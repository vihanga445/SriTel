package com.example.payment_service.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody Map<String, Object> paymentRequest) {
        // Logic: Simulate processing logic (e.g., validate card number)
        System.out.println("Processing payment for amount: " + paymentRequest.get("amount"));

        // Mock Response: Always success
        Map<String, String> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("transactionId", UUID.randomUUID().toString());

        return ResponseEntity.ok(response);
    }


}
