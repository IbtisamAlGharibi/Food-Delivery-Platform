package com.fooddelivery.Controllers;

import com.fooddelivery.Services.CustomerService;
import com.fooddelivery.Services.DeliveryService;
import com.fooddelivery.Services.OrderService;
import com.fooddelivery.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
