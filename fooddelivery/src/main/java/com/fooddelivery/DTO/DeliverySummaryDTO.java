package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySummaryDTO {
    private int trackingCode;
    private String status;
    private  LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    public static DeliveryResponseDTO fromEntity(Delivery delivery){
        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setTrackingCode(delivery.getTrackingCode());
        dto.setStatus(delivery.getStatus());
        dto.setPickedUpAt(delivery.getPickedUpAt());
        dto.setDeliveredAt(delivery.getDeliveredAt());

        return dto;
    }
}
