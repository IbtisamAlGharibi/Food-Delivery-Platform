package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.OrderItem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {
    private int quantity;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double unitPrice;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double totalPrice;
    private String specialInstructions;

    public OrderItem toEntity() {

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setTotalPrice(totalPrice);
        orderItem.setSpecialInstructions(specialInstructions);

        return orderItem;
    }

    public void applyTo(OrderItem orderItem) {
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setTotalPrice(totalPrice);
        orderItem.setSpecialInstructions(specialInstructions);
    }
}
