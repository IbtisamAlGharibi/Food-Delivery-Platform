package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboMeal {
    private String comboName;
    private String description;
    private double totalPrice;
    private boolean isAvailable;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
}
