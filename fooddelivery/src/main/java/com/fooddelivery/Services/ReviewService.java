package com.fooddelivery.Services;

import com.fooddelivery.DTO.ResponseDTOs.ReviewResponseDTO;
import com.fooddelivery.Entities.*;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public List<ReviewResponseDTO> getDriverReviews(Integer driverId) {
        deliveryDriverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
        List<Review> reviews = reviewRepository.findByDeliveryDriverIdAndIsActiveTrue(driverId);
        List<ReviewResponseDTO> responseList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewResponseDTO dto = ReviewResponseDTO.fromEntity(review);
            responseList.add(dto);
        }
        return responseList;
    }
    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        review.setActive(false);
        review.setUpdatedDate(new Date());
        reviewRepository.save(review);
    }
    public Double getRestaurantAverageRating(Integer restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        Double avg = reviewRepository.getRestaurantAverage(restaurantId);
        return avg != null ? avg : 0.0;
    }
    public Double getDriverAverageRating(Integer driverId) {
        deliveryDriverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
        Double avg = reviewRepository.getDriverAverage(driverId);
        return avg != null ? avg : 0.0;
    }
    public Page<ReviewResponseDTO> getRestaurantReviews(Integer restaurantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewRepository.findByRestaurantIdAndIsActiveTrue(restaurantId, pageable);

        return reviews.map(ReviewResponseDTO::fromEntity);
    }
}
