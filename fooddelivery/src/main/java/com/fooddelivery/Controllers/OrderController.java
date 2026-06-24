package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.ResponseDTOs.OrderResponseDTO;
import com.fooddelivery.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/customer/{customerId}/restaurant/{restaurantId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Integer customerId,
            @PathVariable Integer restaurantId) {
        OrderResponseDTO order = orderService.createOrder(customerId, restaurantId, Collections.emptyList());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
