package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Order;

import java.util.Date;

public class OrderSummaryDTO {
    private int orderCode;
    private Date orderDate;
    private String status;
    private double deliveryFee;
    private double totalAmount;
    private boolean  isActive;

    public static OrderResponseDTO fromEntity(Order order){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderCode(order.getOrderCode());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setDeliveryFee(order.getDeliveryFee());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setActive(order.isActive());

        return dto;
    }
}
