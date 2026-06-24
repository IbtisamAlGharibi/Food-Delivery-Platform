package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.RequestDTOs.OrderItemRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.OrderResponseDTO;
import com.fooddelivery.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/{id}/items/{menuItemId}")
    public ResponseEntity<OrderResponseDTO> addItem(@PathVariable Integer id, @PathVariable Integer menuItemId,
            @Valid @RequestBody OrderItemRequestDTO dto) {
        return ResponseEntity.ok(orderService.addMenuItemToOrder(id, menuItemId, dto.getQuantity()));
    }
    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Integer id, @PathVariable Integer itemId) {
        orderService.removeMenuItemFromOrder(id, itemId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/discount/{amount}")
    public ResponseEntity<OrderResponseDTO> applyDiscount(@PathVariable Integer id, @PathVariable double amount) {
        return ResponseEntity.ok(orderService.applyDiscount(id, amount));
    }
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.confirmOrder(id));
    }
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Integer id, @PathVariable String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
