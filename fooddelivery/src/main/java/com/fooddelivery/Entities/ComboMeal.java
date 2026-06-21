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
public class ComboMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comboName;
    private String description;
    private double totalPrice;
    private boolean isAvailable;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;

    @ManyToOne
    private Restaurant restaurant;
    @ManyToMany
    private List<MenuItem> menuItemList;
}
