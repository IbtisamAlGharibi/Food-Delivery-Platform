package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int customerCode;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;
    private int loyaltyPoints;

}
