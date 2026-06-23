package com.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemCode;

    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String specialInstructions;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
    @ManyToOne
    private Order order;
    @ManyToOne
    private MenuItem menuItem;
}
