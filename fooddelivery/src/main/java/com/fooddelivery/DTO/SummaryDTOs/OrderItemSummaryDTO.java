package com.fooddelivery.DTO.SummaryDTOs;

import com.fooddelivery.Entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSummaryDTO {
    private int itemCode;
    private int quantity;
    private double totalPrice;

    public static OrderItemSummaryDTO fromEntity(OrderItem orderItem) {

        OrderItemSummaryDTO dto = new OrderItemSummaryDTO();
        dto.setItemCode(orderItem.getItemCode());
        dto.setQuantity(orderItem.getQuantity());
        dto.setTotalPrice(orderItem.getTotalPrice());

        return dto;
    }
}
