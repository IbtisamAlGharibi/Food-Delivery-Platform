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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String targetType;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    private DeliveryDriver deliveryDriver;
}
