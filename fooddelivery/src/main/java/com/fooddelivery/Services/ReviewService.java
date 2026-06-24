package com.fooddelivery.Services;

import com.fooddelivery.DTO.ResponseDTOs.ReviewResponseDTO;
import com.fooddelivery.Entities.*;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    CustomerRepository customerRepository;
    RestaurantRepository restaurantRepository;
    OrderRepository orderRepository;
    DeliveryRepository deliveryRepository;
    DeliveryDriverRepository deliveryDriverRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CustomerRepository customerRepository,
                         RestaurantRepository restaurantRepository, OrderRepository orderRepository,
                         DeliveryRepository deliveryRepository, DeliveryDriverRepository deliveryDriverRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.deliveryRepository= deliveryRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
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
    public ReviewResponseDTO leaveDriverReview(Integer customerId, Integer driverId,
            int rating, String comment){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        DeliveryDriver driver = new DeliveryDriver();
        List<Delivery> deliveries = deliveryRepository.findAll();

        for (Delivery delivery : deliveries) {
            if (delivery.getDeliveryDriver() != null &&
                    delivery.getDeliveryDriver().getDriverCode() == driverId) {
                driver = delivery.getDeliveryDriver();
                break;
            }
        }
        Review review = new Review();
        review.setCustomer(customer);
        review.setDeliveryDriver(driver);
        review.setTargetType("DRIVER");
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        review = reviewRepository.save(review);

        return ReviewResponseDTO.fromEntity(review);
    }
    public List<ReviewResponseDTO> getRestaurantReviews(Integer restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        List<Review> reviews = reviewRepository.findByRestaurantIdAndIsActiveTrue(restaurantId);
        List<ReviewResponseDTO> responseList = new ArrayList<>();
        for (Review review : reviews) {
            ReviewResponseDTO dto = ReviewResponseDTO.fromEntity(review);
            responseList.add(dto);
        }
        return responseList;
    }
}
