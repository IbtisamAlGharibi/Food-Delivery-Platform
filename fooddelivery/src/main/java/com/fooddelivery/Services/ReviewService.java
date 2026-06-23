package com.fooddelivery.Services;

import com.fooddelivery.Repositories.CustomerRepository;
import com.fooddelivery.Repositories.OrderRepository;
import com.fooddelivery.Repositories.RestaurantRepository;
import com.fooddelivery.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    CustomerRepository customerRepository;
    RestaurantRepository restaurantRepository;
    OrderRepository orderRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CustomerRepository customerRepository,
                         RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }
}
