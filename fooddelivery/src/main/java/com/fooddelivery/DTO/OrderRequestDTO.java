package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Order;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    @DecimalMin("0.0")
    private double deliveryFee;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double discountAmount;
    @PositiveOrZero
    @DecimalMin("0.0")
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
