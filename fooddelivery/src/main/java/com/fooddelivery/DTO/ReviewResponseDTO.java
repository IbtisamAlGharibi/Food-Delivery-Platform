package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private int id;
    private String targetType;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private Date createdDate;
    private Date updatedDate;

    public static ReviewResponseDTO fromEntity(Review review) {

        ReviewResponseDTO dto = new ReviewResponseDTO();

        dto.setId(review.getId());
        dto.setTargetType(review.getTargetType());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setCreatedDate(review.getCreatedDate());
        dto.setUpdatedDate(review.getUpdatedDate());

        return dto;
    }
}
