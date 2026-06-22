package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Payment;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private int transactionRef;
    private String paymentMethod;
    private String status;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double amount;

    public Payment toEntity() {
        Payment payment = new Payment();
        payment.setTransactionRef(transactionRef);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(status);
        payment.setAmount(amount);

        return payment;
    }

    public void applyTo(Payment payment) {
        payment.setTransactionRef(transactionRef);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(status);
        payment.setAmount(amount);
    }
}
