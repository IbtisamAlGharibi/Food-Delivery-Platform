package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Delivery;
import com.fooddelivery.Entities.DeliveryDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDriverSummaryDTO {
    private int driverId;
    private String firstName;
    private String lastName;
    private String vehicleType;
    private boolean isOnline;
    private boolean  isActive;

    public static DeliveryDriverResponseDTO fromEntity(DeliveryDriver deliveryDriver) {
        DeliveryDriverResponseDTO dto = new DeliveryDriverResponseDTO();

        dto.setDriverId(deliveryDriver.getDriverCode());
        dto.setFirstName(deliveryDriver.getFirstName());
        dto.setLastName(deliveryDriver.getLastName());
        dto.setVehicleType(deliveryDriver.getVehicleType());
        dto.setOnline(deliveryDriver.isOnline());
        dto.setActive(dto.isActive());

        return dto;
    }
}
