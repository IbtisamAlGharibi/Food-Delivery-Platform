package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Restaurant;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDTO {
    private String name;
    private String description;
    private String cuisineType;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private int minOrderAmount;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double deliveryFee;
    private boolean acceptingOrders;

    public Restaurant toEntity() {
        Restaurant restaurant= new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setCuisineType(cuisineType);
        restaurant.setOpeningTime(openingTime);
        restaurant.setClosingTime(closingTime);
        restaurant.setMinOrderAmount(minOrderAmount);
        restaurant.setDeliveryFee(deliveryFee);
        restaurant.setAcceptingOrders(acceptingOrders);

        return restaurant;
    }

    public void applyTo(Restaurant restaurant){
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setCuisineType(cuisineType);
        restaurant.setOpeningTime(openingTime);
        restaurant.setClosingTime(closingTime);
        restaurant.setMinOrderAmount(minOrderAmount);
        restaurant.setDeliveryFee(deliveryFee);
        restaurant.setAcceptingOrders(acceptingOrders);
    }
}
