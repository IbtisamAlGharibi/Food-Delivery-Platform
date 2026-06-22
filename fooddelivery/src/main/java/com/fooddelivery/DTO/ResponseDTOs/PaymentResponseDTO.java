package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {

    private int id;
    private int transactionRef;
    private String paymentMethod;
    private String status;
    private double amount;
    private LocalDateTime processedAt;
    private Date createdDate;
    private Date updatedDate;

    public static PaymentResponseDTO fromEntity(Payment payment) {

        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setId(payment.getId());
        dto.setTransactionRef(payment.getTransactionRef());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setAmount(payment.getAmount());
        dto.setProcessedAt(payment.getProcessedAt());
        dto.setCreatedDate(payment.getCreatedDate());
        dto.setUpdatedDate(payment.getUpdatedDate());

        return dto;
    }
}
