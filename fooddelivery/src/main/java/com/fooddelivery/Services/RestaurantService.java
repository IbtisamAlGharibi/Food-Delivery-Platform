package com.fooddelivery.Services;

import com.fooddelivery.DTO.RequestDTOs.RestaurantRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.RestaurantResponseDTO;
import com.fooddelivery.Entities.Restaurant;
import com.fooddelivery.Entities.RestaurantOwner;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto, Integer ownerId){
        RestaurantOwner owner = restaurantRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found")).getRestaurantOwner();

        Restaurant restaurant = dto.toEntity();
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setCuisineType(dto.getCuisineType());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
        restaurant.setMinOrderAmount(dto.getMinOrderAmount());
        restaurant.setDeliveryFee(dto.getDeliveryFee());
        restaurant.setAcceptingOrders(dto.isAcceptingOrders());
        restaurant.setRestaurantOwner(owner);
        restaurant = restaurantRepository.save(restaurant);

        return RestaurantResponseDTO.fromEntity(restaurant);
    }
}
