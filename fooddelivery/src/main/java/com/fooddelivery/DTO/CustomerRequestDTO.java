package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;
    private int loyaltyPoints;

    public Customer toEntity() {
        Customer customer =new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPasswordHash(passwordHash);
        customer.setLoyaltyPoints(loyaltyPoints);
        return customer;
    }

    public void applyTo(Customer customer){
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPasswordHash(passwordHash);
        customer.setLoyaltyPoints(loyaltyPoints);
    }
}
