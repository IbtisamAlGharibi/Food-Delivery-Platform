package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    @Query("select mi from MenuItem mi where mi.isActive= true and mi.restaurant.id=:id")
    List<MenuItem> findByRestaurantId(@Param("id") Integer id);
    @Query("select mi from MenuItem mi where mi.isAvailable= true and mi.restaurant.id=:id")
    List<MenuItem> findByRestaurantIdAndIsAvailableTrue(@Param("id") Integer id);
    @Query("select mi from MenuItem mi where mi.isVegetarian= true")
    List<MenuItem> findByIsVegetarianTrue();
    @Query("SELECT mi FROM MenuItem mi WHERE mi.isActive=true AND mi.price BETWEEN :min AND :max")
    List<MenuItem> findByPriceBetween(double min, double max);
    @Query("""
       select mi
       from MenuItem mi
       where mi.isActive=true
       and lower(mi.name)
       like lower(concat('%',:keyword,'%'))
       and mi.calories
       between :minCalories
       and :maxCalories
       """)
    List<MenuItem> searchMenuItems(String keyword, double minCalories, double maxCalories);
}
