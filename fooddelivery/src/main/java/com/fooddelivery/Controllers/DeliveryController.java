package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.ResponseDTOs.DeliveryResponseDTO;
import com.fooddelivery.Services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    DeliveryService deliveryService;
    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    @PostMapping("/order/{orderId}/assignManual/{driverId}")
    public ResponseEntity<DeliveryResponseDTO> assignManual(@PathVariable Integer orderId, @PathVariable Integer driverId) {
        return ResponseEntity.ok(deliveryService.assignDriverToOrder(orderId, driverId));
    }
    @PostMapping("/order/{orderId}/assignAuto")
    public ResponseEntity<DeliveryResponseDTO> assignAuto(@PathVariable Integer orderId) {
        return ResponseEntity.ok(deliveryService.autoAssignDriver(orderId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(deliveryService.getDeliveryById(id));
    }
    @PutMapping("/{id}/pickup")
    public ResponseEntity<DeliveryResponseDTO> pickup(@PathVariable Integer id) {
        return ResponseEntity.ok(deliveryService.markDeliveryPickedUp(id));
    }
    @PutMapping("/{id}/complete")
    public ResponseEntity<DeliveryResponseDTO> complete(@PathVariable Integer id) {
        return ResponseEntity.ok(deliveryService.markDeliveryDelivered(id));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<DeliveryResponseDTO>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(deliveryService.getDeliveriesByStatus(status));
    }
}
