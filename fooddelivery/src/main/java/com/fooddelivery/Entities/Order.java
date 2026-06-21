package com.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;

    private Date orderDate;
    private String status;
    private double subtotal;
    private double deliveryFee;
    private double discountAmount;
    private double totalAmount;
    private String deliveryNotes;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Restaurant restaurant;
    @OneToMany
    private List<OrderItem> orderItemList;
    @OneToOne
    private Delivery delivery;
    @OneToOne
    private Payment payment;
}
