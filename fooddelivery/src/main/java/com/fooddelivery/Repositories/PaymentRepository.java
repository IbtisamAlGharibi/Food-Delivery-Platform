package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("select p from Payment p where p.order.orderCode=:orderId")
    public Payment findByOrderId(Integer orderId);
    @Query("""
       select p
       from Payment p
       where p.isActive=true
       and p.paymentMethod =:method
       and p.status =:status
       and p.createdDate between :from and :to
       """)
    Page<Payment> searchPayments(String method, String status, Date from, Date to, Pageable pageable);
}
