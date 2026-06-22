package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseDTO {
    private int trackingCode;
    private String status;
    private LocalDateTime assignedAt;
    private  LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;
    private boolean  isActive;

    public static DeliveryResponseDTO fromEntity(Delivery delivery){
        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setTrackingCode(delivery.getTrackingCode());
        dto.setStatus(delivery.getStatus());
        dto.setAssignedAt(delivery.getAssignedAt());
        dto.setPickedUpAt(delivery.getPickedUpAt());
        dto.setDeliveredAt(delivery.getDeliveredAt());
        dto.setActive(delivery.isActive());

        return dto;
    }
}
