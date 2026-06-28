package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.RequestDTOs.ComboMealRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.MenuItemRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.RestaurantRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.ComboMealResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.MenuItemResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.RestaurantResponseDTO;
import com.fooddelivery.Services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @PostMapping("/owner/{ownerId}")
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@PathVariable String ownerId,
            @Valid @RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO restaurant = restaurantService.createRestaurant(dto, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable Integer id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<RestaurantResponseDTO>> getByCuisine(@PathVariable String cuisine) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByCuisine(cuisine));
    }
    @PutMapping("/{id}/toggle-orders")
    public ResponseEntity<RestaurantResponseDTO> toggleOrders(@PathVariable Integer id, @RequestParam boolean accepting) {
        return ResponseEntity.ok(restaurantService.toggleAcceptingOrders(id, accepting));
    }
    @PutMapping("/{id}/fee/{newFee}")
    public ResponseEntity<RestaurantResponseDTO> updateDeliveryFee(@PathVariable Integer id, @PathVariable double newFee) {
        return ResponseEntity.ok(restaurantService.updateDeliveryFee(id, newFee));
    }
    @GetMapping("/{id}/menu")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenu(@PathVariable Integer id) {
        return ResponseEntity.ok(restaurantService.getMenuForRestaurant(id));
    }
    @GetMapping("/{id}/combos")
    public ResponseEntity<List<ComboMealResponseDTO>> getCombos(@PathVariable Integer id) {
        return ResponseEntity.ok(restaurantService.getCombosForRestaurant(id));
    }
    @PostMapping("/{id}/menu")
    public ResponseEntity<MenuItemResponseDTO> addMenuItem(@PathVariable Integer id,
            @Valid @RequestBody MenuItemRequestDTO dto) {
        MenuItemResponseDTO item = restaurantService.addMenuItem(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    @PutMapping("/menu/{itemId}/available")
    public ResponseEntity<MenuItemResponseDTO> updateAvailability(@PathVariable Integer itemId,
            @RequestParam boolean status) {
        return ResponseEntity.ok(restaurantService.updateMenuItemAvailability(itemId, status));
    }
    @PostMapping("/{id}/combos")
    public ResponseEntity<ComboMealResponseDTO> createComboMeal(@PathVariable Integer id, @Valid @RequestBody ComboMealRequestDTO dto) {
        ComboMealResponseDTO comboMeal = restaurantService.createComboMeal(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comboMeal);
    }
    @PutMapping("/{id}/bulk-price-increase")
    public ResponseEntity<Void> bulkPriceIncrease(@PathVariable Integer id, @RequestParam double percentage) {
        restaurantService.bulkUpdateMenuItemPrices(id, percentage);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/menu/topSellers")
    public ResponseEntity<List<MenuItemResponseDTO>> getTopSellingItems(@PathVariable Integer id) {
        return ResponseEntity.ok(restaurantService.getTopSellingItems(id));
    }
    @GetMapping("/menu/search")
    public ResponseEntity<List<MenuItemResponseDTO>> searchMenuItems(@RequestParam String keyword, @RequestParam double minCalories,
            @RequestParam double maxCalories) {
        return ResponseEntity.ok(restaurantService.searchMenuItems(keyword, minCalories, maxCalories));
    }
}
