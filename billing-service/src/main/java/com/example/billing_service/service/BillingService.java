package com.example.billing_service.service;


import com.example.billing_service.config.RabbitMQConfig;
import com.example.billing_service.entity.Bill;
import com.example.billing_service.repository.BillRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillingService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //create a dummy bill
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    //get bills for user
    public List<Bill> getUserBills(Long userId) {
        return billRepository.findByUserId(userId);
    }

    //pay bill
    public String payBill(Long billId){
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));

        if("PAID".equals(bill.getStatus())){
            return "Bill is already payed";
        }
        // B. Prepare Request for Payment Service
        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("amount", bill.getAmount());
        paymentRequest.put("billId", billId);

        String paymentUrl = "http://PAYMENT-SERVICE/api/payments";
        Map<String, String> response = restTemplate.postForObject(paymentUrl, paymentRequest, Map.class);

        // D. Update Bill Status if successful
        if (response != null && "SUCCESS".equals(response.get("status"))) {
            bill.setStatus("PAID");
            billRepository.save(bill);

            String message = "Bill ID " + billId + " paid successfully. Transaction: " + response.get("transactionId");

            // Send to Exchange with specific Routing Key
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
            System.out.println("Message published to RabbitMQ");

            return "Payment Successful. Transaction ID: " + response.get("transactionId");
        } else {
            return "Payment Failed";
        }


    }

}
