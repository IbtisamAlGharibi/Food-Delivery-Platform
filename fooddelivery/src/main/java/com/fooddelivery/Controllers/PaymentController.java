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
    @PutMapping("/{paymentId}/complete")
    public ResponseEntity<PaymentResponseDTO> completePayment(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.completePayment(paymentId));
    }
    @PutMapping("/{paymentId}/refund")
    public ResponseEntity<PaymentResponseDTO> refundPayment(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.refundPayment(paymentId));
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrder(orderId));
    }
}
