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
public class DeliveryDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverCode;

    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;
    private String vehicleType;
    private String vehiclePlate;
    private String currentLat;
    private String currentLng;
    private boolean isOnline;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;

    @OneToMany
    private List<Delivery> deliveryList;
}
