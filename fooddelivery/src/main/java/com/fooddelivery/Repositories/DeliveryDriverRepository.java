package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver,Integer> {
    @Query("select d from DeliveryDriver d where d.isActive=true")
    List<DeliveryDriver> getAllDrivers();

    @Query("select d from DeliveryDriver d where d.isActive=true and d.isOnline=true")
    List<DeliveryDriver> getOnlineDrivers();
    @Query(value = " select distinct d from DeliveryDriver d  join d.deliveryList dl where d.isActive=true and dl.status='COMPLETED'")
    List<DeliveryDriver> getDriverLeaderboard();
}
