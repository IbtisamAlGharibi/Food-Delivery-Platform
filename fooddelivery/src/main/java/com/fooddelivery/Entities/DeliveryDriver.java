package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDriver {
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
}
