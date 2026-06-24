package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.ResponseDTOs.PaymentResponseDTO;
import com.fooddelivery.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
 PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> createPayment(@PathVariable Integer orderId, @RequestParam String method) {
        PaymentResponseDTO response = paymentService.processPayment(orderId, method);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
