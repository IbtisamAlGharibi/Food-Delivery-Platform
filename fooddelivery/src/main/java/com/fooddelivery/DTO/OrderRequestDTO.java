package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Date orderDate;
    private String status;
    private double subtotal;
    private double deliveryFee;
    private double discountAmount;
    private double totalAmount;
    private String deliveryNotes;


    public Order toEntity() {
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setStatus(status);
        order.setSubtotal(subtotal);
        order.setDeliveryFee(deliveryFee);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
        order.setDeliveryNotes(deliveryNotes);

        return order;
    }

    public void applyTo(Order order){
        order.setOrderDate(orderDate);
        order.setStatus(status);
        order.setSubtotal(subtotal);
        order.setDeliveryFee(deliveryFee);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
        order.setDeliveryNotes(deliveryNotes);
    }

}
