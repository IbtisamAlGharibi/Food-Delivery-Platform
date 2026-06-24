package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.ResponseDTOs.ReviewResponseDTO;
import com.fooddelivery.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/restaurant/{restaurantId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> submitRestaurantReview(@PathVariable Integer restaurantId, @PathVariable Integer customerId,
            @RequestParam int rating, @RequestParam String comment) {
        ReviewResponseDTO response = reviewService.leaveRestaurantReview(customerId, restaurantId, rating, comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/driver/{driverId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> submitDriverReview(@PathVariable Integer driverId, @PathVariable Integer customerId,
            @RequestParam int rating, @RequestParam String comment) {
        ReviewResponseDTO response = reviewService.leaveDriverReview(customerId, driverId, rating, comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewResponseDTO>> getRestaurantReviews(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(reviewService.getRestaurantReviews(restaurantId));
    }
}
