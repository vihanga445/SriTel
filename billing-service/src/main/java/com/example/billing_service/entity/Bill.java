package com.example.billing_service.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double amount;
    private LocalDate dueDate;
    private String status; // "PAID", "UNPAID"

    // Constructors
    public Bill() {}
    public Bill(Long userId, Double amount, LocalDate dueDate, String status) {
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters (Manual)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }

}
