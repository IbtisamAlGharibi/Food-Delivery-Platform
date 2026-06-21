package com.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int transactionRef;
    private String paymentMethod;
    private String  status;
    private double amount;
    private LocalDateTime processedAt;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
    @OneToOne
    private Order order;
}
