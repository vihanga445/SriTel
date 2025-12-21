package com.example.provisioning_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class ServiceSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String serviceName; // e.g., "ROAMING", "DATA_TOPUP"
    private String status;      // "ACTIVE", "INACTIVE"
    private LocalDate activationDate;

    public ServiceSubscription() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getActivationDate() { return activationDate; }
    public void setActivationDate(LocalDate activationDate) { this.activationDate = activationDate; }



}
