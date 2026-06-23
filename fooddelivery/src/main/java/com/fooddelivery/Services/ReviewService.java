package com.fooddelivery.Services;

import com.fooddelivery.DTO.ResponseDTOs.ReviewResponseDTO;
import com.fooddelivery.Entities.Customer;
import com.fooddelivery.Entities.Restaurant;
import com.fooddelivery.Entities.Review;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.CustomerRepository;
import com.fooddelivery.Repositories.OrderRepository;
import com.fooddelivery.Repositories.RestaurantRepository;
import com.fooddelivery.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public ReviewResponseDTO leaveRestaurantReview(Integer customerId,
                                                   Integer restaurantId, int rating,String comment){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        Review review = new Review();
        review.setCustomer(customer);
        review.setRestaurant(restaurant);
        review.setTargetType("RESTAURANT");
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        review = reviewRepository.save(review);

        return ReviewResponseDTO.fromEntity(review);
    }
}
