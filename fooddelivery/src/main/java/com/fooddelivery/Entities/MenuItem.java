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
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    private double calories;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
    @ManyToOne
    private Restaurant restaurant;
    @OneToMany
    private List<OrderItem> orderItemList;
}
