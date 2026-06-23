package com.fooddelivery.Services;

import com.fooddelivery.DTO.RequestDTOs.RestaurantRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.RestaurantResponseDTO;
import com.fooddelivery.Entities.Restaurant;
import com.fooddelivery.Entities.RestaurantOwner;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public RestaurantResponseDTO toggleAcceptingOrders(Integer restaurantId, boolean status){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setAcceptingOrders(status);
        restaurant = restaurantRepository.save(restaurant);

        return RestaurantResponseDTO.fromEntity(restaurant);
    }
    public RestaurantResponseDTO updateDeliveryFee(Integer restaurantId, double newFee){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setDeliveryFee(newFee);
        restaurant = restaurantRepository.save(restaurant);

        return RestaurantResponseDTO.fromEntity(restaurant);
    }
    public List<RestaurantResponseDTO> getRestaurantsByCuisine(String cuisine){
        List<Restaurant> restaurants = restaurantRepository.findByCuisineTypeIgnoreCase(cuisine);
        List<RestaurantResponseDTO> result = new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            result.add(RestaurantResponseDTO.fromEntity(restaurant));
        }
        return result;
    }
}
