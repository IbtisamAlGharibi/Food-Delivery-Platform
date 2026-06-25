package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.ResponseDTOs.CustomerResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.DeliveryDriverResponseDTO;
import com.fooddelivery.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    RestaurantService restaurantService;
    OrderService orderService;
    CustomerService customerService;
    DeliveryService deliveryService;
    ReviewService reviewService;

    @Autowired
    public ReportingController(RestaurantService restaurantService, OrderService orderService,
                               CustomerService customerService, DeliveryService deliveryService, ReviewService reviewService) {
        this.restaurantService = restaurantService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.deliveryService = deliveryService;
        this.reviewService=reviewService;
    }
    @GetMapping("/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Double> getRestaurantRevenue(@PathVariable Integer restaurantId,@RequestParam Date date) {
        return ResponseEntity.ok(restaurantService.getRestaurantRevenue(restaurantId,date));
    }
    @GetMapping("/orders/count/restaurant/{restaurantId}")
    public ResponseEntity<Long> getRestaurantOrderCount(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantOrderCount(restaurantId));
    }
    @GetMapping("/customers/topLoyalty")
    public ResponseEntity<List<CustomerResponseDTO>> getTopLoyalCustomers() {
        return ResponseEntity.ok(Collections.singletonList(customerService.getTopLoyalCustomers()));
    }
    @GetMapping("/drivers/leaderboard")
    public ResponseEntity<List<DeliveryDriverResponseDTO>> getDriverLeaderboard() {
        return ResponseEntity.ok(deliveryService.getDriverLeaderboard());
    }
    @GetMapping("/platform/dailySummary/orders")
    public ResponseEntity<Long> getPlatformOrderCount(@RequestParam Date start, @RequestParam Date end) {
        return ResponseEntity.ok(orderService.getPlatformOrderCount(start, end));
    }
    @GetMapping("/platform/dailySummary/fees")
    public ResponseEntity<Double> getPlatformFees(@RequestParam Date start, @RequestParam Date end) {
        return ResponseEntity.ok(orderService.getPlatformDeliveryFees(start,end));
    }
    @GetMapping("/revenue/restaurant/{restaurantID}")
    public ResponseEntity<Double> getRestaurantRevenue(@PathVariable Integer restaurantId, @RequestParam Date from,
            @RequestParam Date to) {
        return ResponseEntity.ok(reviewService.getRestaurantRevenue(restaurantId, from, to));
    }
}
