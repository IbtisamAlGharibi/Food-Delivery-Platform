package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int itemCode;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String specialInstructions;

}
