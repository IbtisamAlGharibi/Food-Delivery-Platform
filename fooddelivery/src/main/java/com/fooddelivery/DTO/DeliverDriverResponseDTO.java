package com.fooddelivery.DTO;

import com.fooddelivery.Entities.DeliveryDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverDriverResponseDTO {
    private int driverId;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String vehicleType;
    private String vehiclePlate;
    private String currentLat;
    private String currentLng;
    private boolean isOnline;
    private boolean  isActive;


    public static DeliverDriverResponseDTO fromEntity(DeliveryDriver deliveryDriver) {

        DeliverDriverResponseDTO dto = new DeliverDriverResponseDTO();

        dto.setDriverId(deliveryDriver.getDriverCode());
        dto.setFirstName(deliveryDriver.getFirstName());
        dto.setLastName(deliveryDriver.getLastName());
        dto.setEmail(deliveryDriver.getEmail());
        dto.setPhone(deliveryDriver.getPhone());
        dto.setVehicleType(deliveryDriver.getVehicleType());
        dto.setVehiclePlate(deliveryDriver.getVehiclePlate());
        dto.setCurrentLat(deliveryDriver.getCurrentLat());
        dto.setCurrentLng(deliveryDriver.getCurrentLng());
        dto.setOnline(deliveryDriver.isOnline());
        dto.setActive(dto.isActive());

        return dto;
    }
}
