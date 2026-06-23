package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {
    private int itemCode;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String specialInstructions;

    public static OrderItemResponseDTO fromEntity(OrderItem orderItem) {

        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setItemCode(orderItem.getItemCode());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setTotalPrice(orderItem.getTotalPrice());
        dto.setSpecialInstructions(orderItem.getSpecialInstructions());

        return dto;
    }
}
