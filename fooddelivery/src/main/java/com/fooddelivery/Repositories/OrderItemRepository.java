package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(" select oi from OrderItem oi where oi.order.restaurant.id=:restaurantId order by oi.quantity desc")
    List<OrderItem> getTopSellingItems(Integer restaurantId);
}
