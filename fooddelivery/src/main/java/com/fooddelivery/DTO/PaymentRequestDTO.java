package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Payment;
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
