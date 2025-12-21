package com.example.provisioning_service.service;


import com.example.provisioning_service.entity.ServiceSubscription;
import com.example.provisioning_service.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvisioningService {

    @Autowired
    private SubscriptionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<ServiceSubscription> getUserServices(Long userId) {
        return repository.findByUserId(userId);
    }

    public ServiceSubscription activateService(Long userId, String serviceName) {
        // 1. Call External Vendor System
        String vendorUrl = "http://MOCK-PROVISIONING-SYSTEM/api/external/provisioning";

        Map<String, Object> request = new HashMap<>();
        request.put("userId", userId);
        request.put("serviceName", serviceName);
        request.put("action", "ACTIVATE");

        // The @LoadBalanced RestTemplate resolves the URL via Eureka
        Map<String, String> response = restTemplate.postForObject(vendorUrl, request, Map.class);

        // 2. If Vendor says OK, save to our DB
        if (response != null && "SUCCESS".equals(response.get("status"))) {
            ServiceSubscription sub = new ServiceSubscription();
            sub.setUserId(userId);
            sub.setServiceName(serviceName);
            sub.setStatus("ACTIVE");
            sub.setActivationDate(LocalDate.now());

            return repository.save(sub);
        } else {
            throw new RuntimeException("Failed to activate service at vendor side");
        }
    }





}
