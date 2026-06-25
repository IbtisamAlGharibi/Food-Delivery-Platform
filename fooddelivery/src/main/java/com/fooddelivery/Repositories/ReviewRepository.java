package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("select r from Review r where r.restaurant.isActive=true and r.restaurant.id=:restaurantId")
    List<Review> findByRestaurantIdAndIsActiveTrue(Integer restaurantId);
    @Query("select r from Review r where r.deliveryDriver.isActive=true and r.deliveryDriver.driverCode=:driverId")
    List<Review> findByDeliveryDriverIdAndIsActiveTrue(Integer driverId);
    @Query(" select avg(r.rating) from Review r where r.restaurant.id =:restaurantId and r.isActive=true")
    Double getRestaurantAverage(Integer restaurantId);
    @Query(" select avg(r.rating) from Review r where r.deliveryDriver.driverCode =:driverId and r.isActive = true")
    Double getDriverAverage(Integer driverId);
}
