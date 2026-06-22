package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Order;
import com.fooddelivery.Entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private int orderCode;
    private Date orderDate;
    private String status;
    private double subtotal;
    private double deliveryFee;
    private double discountAmount;
    private double totalAmount;
    private String deliveryNotes;
    private boolean  isActive;

    public static OrderResponseDTO fromEntity(Order order){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderCode(order.getOrderCode());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setSubtotal(order.getSubtotal());
        dto.setDeliveryFee(order.getDeliveryFee());
        dto.setDiscountAmount(order.getDiscountAmount());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDeliveryNotes(order.getDeliveryNotes());
        dto.setActive(order.isActive());

        return dto;
    }
}
