package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String targetType;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
}
