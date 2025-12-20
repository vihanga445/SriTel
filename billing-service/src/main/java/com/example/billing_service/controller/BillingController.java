package com.example.billing_service.controller;


import com.example.billing_service.entity.Bill;
import com.example.billing_service.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billingService.createBill(bill);
    }

    @GetMapping("/user/{userId}")
    public List<Bill> getBills(@PathVariable Long userId) {
        return billingService.getUserBills(userId);
    }

    @PostMapping("/{billId}/pay")
    public String payBill(@PathVariable Long billId) {
        return billingService.payBill(billId);
    }








}
