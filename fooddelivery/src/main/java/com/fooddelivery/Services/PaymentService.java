package com.fooddelivery.Services;

import com.fooddelivery.DTO.ResponseDTOs.PaymentResponseDTO;
import com.fooddelivery.Entities.Order;
import com.fooddelivery.Entities.Payment;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.OrderRepository;
import com.fooddelivery.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    OrderRepository orderRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    public PaymentResponseDTO processPayment(Integer orderId, String method){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Payment payment = new Payment();
        payment.setTransactionRef(order.getOrderCode());
        payment.setPaymentMethod(method);
        payment.setStatus("PENDING");
        payment.setAmount(order.getTotalAmount());
        payment.setProcessedAt(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        return PaymentResponseDTO.fromEntity(payment);
    }
    public PaymentResponseDTO refundPayment(Integer orderId){
        Payment payment = paymentRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setStatus("REFUNDED");
        payment = paymentRepository.save(payment);

        return PaymentResponseDTO.fromEntity(payment);
    }
    public PaymentResponseDTO completePayment(Integer paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setStatus("PAID");
        payment.setProcessedAt(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        return PaymentResponseDTO.fromEntity(payment);
    }
    public PaymentResponseDTO getPaymentByOrder(Integer orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        return PaymentResponseDTO.fromEntity(payment);
    }
    public Page<PaymentResponseDTO> searchPayments(String method, String status, Date from, Date to, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> payments = paymentRepository.searchPayments(method, status, from, to, pageable);
        return payments.map(PaymentResponseDTO::fromEntity);
    }
}
