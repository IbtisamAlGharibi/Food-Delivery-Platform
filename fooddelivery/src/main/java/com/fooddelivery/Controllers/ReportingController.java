package com.fooddelivery.Controllers;

import com.fooddelivery.Services.CustomerService;
import com.fooddelivery.Services.DeliveryService;
import com.fooddelivery.Services.OrderService;
import com.fooddelivery.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    RestaurantService restaurantService;
    OrderService orderService;
    CustomerService customerService;
    DeliveryService deliveryService;

    @Autowired
    public ReportingController(RestaurantService restaurantService, OrderService orderService,
                               CustomerService customerService, DeliveryService deliveryService) {
        this.restaurantService = restaurantService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.deliveryService = deliveryService;
    }
    @GetMapping("/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Double> getRestaurantRevenue(@PathVariable Integer restaurantId,@RequestParam Date date) {
        return ResponseEntity.ok(restaurantService.getRestaurantRevenue(restaurantId,date));
    }
    @GetMapping("/orders/count/restaurant/{restaurantId}")
    public ResponseEntity<Long> getRestaurantOrderCount(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantOrderCount(restaurantId));
    }
}
