package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("select r from Review r where r.restaurant.isActive=true and r.restaurant.id=:restaurantId")
    List<Review> findByRestaurantIdAndIsActiveTrue(Integer restaurantId);
}
