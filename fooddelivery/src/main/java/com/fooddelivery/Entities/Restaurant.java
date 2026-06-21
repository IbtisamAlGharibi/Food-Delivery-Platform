package com.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String cuisineType;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private int minOrderAmount;
    private double deliveryFee;
    private boolean acceptingOrders;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;

    @ManyToOne
    private RestaurantOwner restaurantOwner;
    @OneToMany
    private List<MenuItem> menuItemList;
    @OneToMany
    private List<ComboMeal> comboMealList;
}
