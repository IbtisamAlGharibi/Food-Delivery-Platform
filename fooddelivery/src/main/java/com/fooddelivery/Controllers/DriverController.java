package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.RequestDTOs.DeliveryDriverRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.DeliveryDriverResponseDTO;
import com.fooddelivery.Services.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    DeliveryService deliveryService;
    @Autowired
    public DriverController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    @PostMapping
    public ResponseEntity<DeliveryDriverResponseDTO> createDriver(@Valid @RequestBody DeliveryDriverRequestDTO dto) {
        DeliveryDriverResponseDTO driver = deliveryService.createDriver(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver);
    }
    @GetMapping
    public ResponseEntity<List<DeliveryDriverResponseDTO>> getAllDrivers() {
        return ResponseEntity.ok(deliveryService.getAllDrivers());
    }
}
