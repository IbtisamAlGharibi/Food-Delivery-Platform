package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select o from Order o where o.isActive=true and o.customer.customerCode=:customerId")
    Order findByCustomerId(@Param("customerId") Integer customerId);
    @Query("select o from Order o where o.isActive=true and o.status=:status and o.restaurant.id=:restaurantId")
    List<Order> findByRestaurantIdAndStatus(@Param("restaurantId") Integer restaurantId, @Param("status") String status);
    @Query("select o from Order o where o.isActive=true and o.orderDate between :start and :end")
    List<Order> findByOrderDateBetween(@Param("start") Date start, @Param("end") Date end);
    @Query("select count(o) from Order o where o.isActive=true and o.restaurant.id=:restaurantId and o.status='COMPLETED'")
    Long totalCompletedOrdersForRestaurant(@Param("restaurantId") Integer restaurantId);
    @Query("select sum(o) from Order o where o.isActive=true and o.restaurant.id=:restaurantId and o.delivery.deliveredAt=:date")
    List<Order> totalDeliveredOrdersInDate(@Param("restaurantId") Integer restaurantId, @Param("date") Date date);
    @Query("select sum(o.deliveryFee) from Order o where o.isActive=true and o.orderDate between :start and :end")
    Double totalDeliveryFeesForDate(@Param("start") Date start, @Param("end") Date end);
    @Query("""
       select sum(o.totalAmount)
       from Order o
       where o.restaurant.id =:restaurantId
       and o.orderDate between :from and :to
       and o.status = 'COMPLETED'
       and o.isActive=true
       """)
    Double getRestaurantRevenue(Integer restaurantId, Date from, Date to);

}
