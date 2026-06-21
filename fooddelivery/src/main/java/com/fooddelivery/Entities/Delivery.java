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
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trackingCode;

    private String status;
    private LocalDateTime assignedAt;
    private  LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
    @OneToOne
    private Order order;
    @ManyToOne
    private DeliveryDriver deliveryDriver;
}
