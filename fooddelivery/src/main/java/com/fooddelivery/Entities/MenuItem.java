package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    private double calories;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
}
