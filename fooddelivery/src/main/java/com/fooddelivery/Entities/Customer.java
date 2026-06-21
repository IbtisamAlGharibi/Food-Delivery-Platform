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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerCode;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;
    private int loyaltyPoints;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;

    @OneToMany
    private List<CustomerAddress> customerAddressList;
    @OneToMany
    private  List<Order> orderList;
    @OneToMany
    private List<Review> reviewList;
}
