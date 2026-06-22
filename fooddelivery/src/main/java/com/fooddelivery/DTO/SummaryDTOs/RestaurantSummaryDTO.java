package com.fooddelivery.DTO.SummaryDTOs;

import com.fooddelivery.DTO.ResponseDTOs.RestaurantResponseDTO;
import com.fooddelivery.Entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantSummaryDTO {
    private int id;
    private String name;
    private String description;
    private String cuisineType;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private boolean  isActive;

    public static RestaurantResponseDTO fromEntity(Restaurant restaurant){
        RestaurantResponseDTO dto = new RestaurantResponseDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setOpeningTime(restaurant.getOpeningTime());
        dto.setClosingTime(restaurant.getClosingTime());
        dto.setActive(restaurant.isActive());

        return dto;
    }
}
