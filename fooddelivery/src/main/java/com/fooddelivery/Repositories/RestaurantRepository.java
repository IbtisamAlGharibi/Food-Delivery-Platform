package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("select r from Restaurant r where r.isActive and r.cuisineType=:cuisineType")
    List<Restaurant> findByCuisineTypeIgnoreCase(@Param("cuisineType") String cuisineType);

    @Query("select r from Restaurant r where r.acceptingOrders=true ")
    List<Restaurant> findByAcceptingOrdersTrue();
    @Query("Select r from Restaurant r where c.isActive=true and r.deliveryFee<=:fee")
    List<Restaurant>  findByDeliveryFeeLessThanEqual(@Param("fee") double fee);

    @Query("select r from Restaurant r where r.isActive=true and r.restaurantOwner.businessLicenseCode=:id")
    List<Restaurant> allRestaurantOwnedById(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r WHERE r.isActive=true AND r.name LIKE (CONCAT('%', :keyword, '%'))")
    List<Restaurant> searchRestaurantsByName(@Param("keyword") String keyword);
}
