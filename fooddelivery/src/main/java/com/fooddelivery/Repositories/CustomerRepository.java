package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Customer;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
    @Query("SELECT c from Customer c WHERE c.isActive=true AND c.email=:email")
    Customer  findByEmail(@Param("email") String email);

    @Query("Select c from Customer c where c.isActive=true and c.loyaltyPoints>=:points")
    List<Customer> findByLoyaltyPointsGreaterThanEqual(@Param("points") int points);
    @Query("SELECT c FROM Customer c WHERE c.isActive=true AND c.createdDate BETWEEN :createdDate AND CURRENT_TIMESTAMP")
    List<Customer> CustomerRegisterWithDateRange(@Param("createdDate") Date createdDate);
    @Query("select c from Customer c where c.isActive=true")
    List<Customer> getAllCustomers();
}
