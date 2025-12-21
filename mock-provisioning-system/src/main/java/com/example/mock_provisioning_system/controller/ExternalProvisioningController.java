package com.example.mock_provisioning_system.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/external/provisioning")
public class ExternalProvisioningController {

    @PostMapping
    public ResponseEntity<Map<String, String>> provisionService(@RequestBody Map<String, Object> request) {
        System.out.println("VENDOR SYSTEM: Received request to " +
                request.get("action") + " service: " + request.get("serviceName") +
                " for User: " + request.get("userId"));

        // Simulate vendor processing
        Map<String, String> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("referenceId", "VEND-" + System.currentTimeMillis());

        return ResponseEntity.ok(response);
    }

}
