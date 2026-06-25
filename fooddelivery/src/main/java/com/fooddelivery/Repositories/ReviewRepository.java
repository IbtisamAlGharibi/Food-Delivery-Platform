package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("select r from Review r where r.restaurant.isActive=true and r.restaurant.id=:restaurantId")
    List<Review> findByRestaurantIdAndIsActiveTrue(@Param("restaurantId") Integer restaurantId);
    @Query("select r from Review r where r.deliveryDriver.isActive=true and r.deliveryDriver.driverCode=:driverId")
    List<Review> findByDeliveryDriverIdAndIsActiveTrue(@Param("driverId") Integer driverId);
    @Query(" select avg(r.rating) from Review r where r.restaurant.id =:restaurantId and r.isActive=true")
    Double getRestaurantAverage(@Param("restaurantId") Integer restaurantId);
    @Query(" select avg(r.rating) from Review r where r.deliveryDriver.driverCode =:driverId and r.isActive = true")
    Double getDriverAverage(@Param("driverId") Integer driverId);
    @Query("select r from Review r where r.restaurant.isActive=true and r.restaurant.id=:restaurantId")
    Page<Review> findByRestaurantIdAndIsActiveTrue(@Param("restaurantId") Integer restaurantId, Pageable pageable);
}
