package com.example.provisioning_service.controller;


import com.example.provisioning_service.entity.ServiceSubscription;
import com.example.provisioning_service.service.ProvisioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/provisioning")
public class ProvisioningController {
    @Autowired
    private ProvisioningService provisioningService;

    @GetMapping("/{userId}")
    public List<ServiceSubscription> getUserServices(@PathVariable Long userId) {
        return provisioningService.getUserServices(userId);
    }

    @PostMapping("/activate")
    public ServiceSubscription activateService(@RequestBody Map<String, Object> payload) {
        // Expects JSON: { "userId": 1, "serviceName": "ROAMING" }
        Long userId = Long.valueOf(payload.get("userId").toString());
        String serviceName = (String) payload.get("serviceName");

        return provisioningService.activateService(userId, serviceName);
    }
}
