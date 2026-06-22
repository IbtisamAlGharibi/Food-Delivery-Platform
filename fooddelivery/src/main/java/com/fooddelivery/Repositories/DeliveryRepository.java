package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
    @Query("select d from Delivery d where d.isActive=true and d.deliveryDriver.driverCode=:driverId and d.status=:status")
    List<Delivery> findByDeliveryDriverIdAndStatus(Integer driverId, String status);
}
