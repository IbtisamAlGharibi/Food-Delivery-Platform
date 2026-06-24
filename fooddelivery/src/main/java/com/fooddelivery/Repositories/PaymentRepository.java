package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("select p from Pyment p where p.order.orderCode=:orderId")
    public Payment findByOrderId(Integer orderId);
}
