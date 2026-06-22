package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.DeliveryDriver;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeliveryDriverRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Phone number must contain 8-15 digits")
    private int phone;
    @NotBlank(message = "Password is required")
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
