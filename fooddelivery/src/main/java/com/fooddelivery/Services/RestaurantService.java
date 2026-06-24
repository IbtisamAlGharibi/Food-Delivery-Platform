package com.fooddelivery.Services;

import com.fooddelivery.DTO.RequestDTOs.ComboMealRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.MenuItemRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.RestaurantRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.ComboMealResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.MenuItemResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.RestaurantResponseDTO;
import com.fooddelivery.Entities.ComboMeal;
import com.fooddelivery.Entities.MenuItem;
import com.fooddelivery.Entities.Restaurant;
import com.fooddelivery.Entities.RestaurantOwner;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.ComboMealRepository;
import com.fooddelivery.Repositories.MenuItemRepository;
import com.fooddelivery.Repositories.RestaurantOwnerRepository;
import com.fooddelivery.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository;
    MenuItemRepository menuItemRepository;
    RestaurantOwnerRepository restaurantOwnerRepository;
    ComboMealRepository comboMealRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,MenuItemRepository menuItemRepository,RestaurantOwnerRepository restaurantOwnerRepository,ComboMealRepository  comboMealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.restaurantOwnerRepository= restaurantOwnerRepository;
        this.comboMealRepository = comboMealRepository;
    }

    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto, Integer ownerId){
        RestaurantOwner owner = restaurantOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

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
    public List<RestaurantResponseDTO> getRestaurantsUnderDeliveryFee(double maxFee){
        List<Restaurant> restaurants = restaurantRepository.findByDeliveryFeeLessThanEqual(maxFee);
        List<RestaurantResponseDTO> result = new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            result.add(RestaurantResponseDTO.fromEntity(restaurant));
        }
        return result;
    }
    public List<MenuItemResponseDTO> getMenuForRestaurant(Integer restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        List<MenuItemResponseDTO> result = new ArrayList<>();
        for(MenuItem item : restaurant.getMenuItemList()){
            result.add(MenuItemResponseDTO.fromEntity(item));
        }
        return result;
    }
    public void bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        for(MenuItem item : restaurant.getMenuItemList()){
            double newPrice = item.getPrice() + (item.getPrice() * percentageIncrease / 100);
            item.setPrice(newPrice);
            menuItemRepository.save(item);
        }
    }
    public List<RestaurantResponseDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantResponseDTO> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            result.add(RestaurantResponseDTO.fromEntity(restaurant));
        }
        return result;
    }
    public RestaurantResponseDTO getRestaurantById(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        return RestaurantResponseDTO.fromEntity(restaurant);
    }
    public MenuItemResponseDTO addMenuItem(Integer restaurantId, MenuItemRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        MenuItem item = dto.toEntity();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item = menuItemRepository.save(item);

        restaurant.getMenuItemList().add(item);
        restaurantRepository.save(restaurant);

        return MenuItemResponseDTO.fromEntity(item);
    }
    public MenuItemResponseDTO updateMenuItemAvailability(Integer itemId, boolean status) {
        MenuItem item = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        item.setAvailable(status);
        item = menuItemRepository.save(item);

        return MenuItemResponseDTO.fromEntity(item);
    }
    public List<ComboMealResponseDTO> getCombosForRestaurant(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        List<ComboMealResponseDTO> result = new ArrayList<>();
        for (ComboMeal combo : restaurant.getComboMealList()) {
            result.add(ComboMealResponseDTO.fromEntity(combo));
        }
        return result;
    }
    public ComboMealResponseDTO createComboMeal(Integer restaurantId, ComboMealRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        ComboMeal comboMeal = dto.toEntity();
        comboMeal.setComboName(dto.getComboName());
        comboMeal.setDescription(dto.getDescription());
        comboMeal.setTotalPrice(dto.getTotalPrice());
        comboMeal.setAvailable(dto.isAvailable());

        comboMeal.setRestaurant(restaurant);
        comboMeal = comboMealRepository.save(comboMeal);
        restaurant.getComboMealList().add(comboMeal);
        restaurantRepository.save(restaurant);

        return ComboMealResponseDTO.fromEntity(comboMeal);
    }
}
