package com.fooddelivery.Controllers;

import com.fooddelivery.Services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    DeliveryService deliveryService;
    @Autowired
    public DriverController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

}
