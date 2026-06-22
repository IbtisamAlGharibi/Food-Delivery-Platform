package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.ComboMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComboMealRepository extends JpaRepository<ComboMeal,Integer> {
    @Query("SELECT c FROM ComboMeal c JOIN c.menuItemList m WHERE c.isActive = true AND m.id = :menuItemId")
    List<ComboMeal> findComboMealsByMenuItemId(@Param("menuItemId") Integer menuItemId);
}
