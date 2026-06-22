package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeliveryRequestDTO {
    private String status;
    private LocalDateTime assignedAt;
    private  LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    public Delivery toEntity() {
        Delivery delivery = new Delivery();
        delivery.setStatus(status);
        delivery.setAssignedAt(assignedAt);
        delivery.setPickedUpAt(pickedUpAt);
        delivery.setDeliveredAt(deliveredAt);

        return delivery;
    }

    public void applyTo(Delivery delivery){
        delivery.setStatus(status);
        delivery.setAssignedAt(assignedAt);
        delivery.setPickedUpAt(pickedUpAt);
        delivery.setDeliveredAt(deliveredAt);
    }

}
