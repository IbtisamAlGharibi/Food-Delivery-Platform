package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewSummaryDTO {
    private int id;
    private int rating;
    private String comment;

    public static ReviewSummaryDTO fromEntity(Review review) {

        ReviewSummaryDTO dto = new ReviewSummaryDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());

        return dto;
    }
}
