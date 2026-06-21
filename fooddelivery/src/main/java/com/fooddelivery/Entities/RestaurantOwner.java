package com.fooddelivery.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RestaurantOwner {
    @Id
    private String businessLicenseCode;

    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;

    @OneToMany
    private List<Restaurant> restaurantList;
}
