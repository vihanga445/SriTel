package com.example.provisioning_service.repository;

import com.example.provisioning_service.entity.ServiceSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<ServiceSubscription, Long> {


    List<ServiceSubscription> findByUserId(Long userId);



}
