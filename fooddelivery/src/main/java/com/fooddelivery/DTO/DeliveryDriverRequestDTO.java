package com.fooddelivery.DTO;

import com.fooddelivery.Entities.DeliveryDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeliveryDriverRequestDTO {
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

    public DeliveryDriver toEntity() {
        DeliveryDriver deliveryDriver = new DeliveryDriver();

        deliveryDriver.setFirstName(firstName);
        deliveryDriver.setLastName(lastName);
        deliveryDriver.setEmail(email);
        deliveryDriver.setPhone(phone);
        deliveryDriver.setPasswordHash(passwordHash);
        deliveryDriver.setVehicleType(vehicleType);
        deliveryDriver.setVehiclePlate(vehiclePlate);

        return deliveryDriver;
    }

    public void applyTo(DeliveryDriver deliveryDriver) {
        deliveryDriver.setFirstName(firstName);
        deliveryDriver.setLastName(lastName);
        deliveryDriver.setEmail(email);
        deliveryDriver.setPhone(phone);
        deliveryDriver.setPasswordHash(passwordHash);
        deliveryDriver.setVehicleType(vehicleType);
        deliveryDriver.setVehiclePlate(vehiclePlate);
    }
}
