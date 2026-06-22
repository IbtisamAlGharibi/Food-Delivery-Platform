package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {
    private String targetType;
    private int rating;
    private String comment;

    public Review toEntity() {
        Review review = new Review();
        review.setTargetType(targetType);
        review.setRating(rating);
        review.setComment(comment);

        return review;
    }

    public void applyTo(Review review) {
        review.setTargetType(targetType);
        review.setRating(rating);
        review.setComment(comment);
    }
}
