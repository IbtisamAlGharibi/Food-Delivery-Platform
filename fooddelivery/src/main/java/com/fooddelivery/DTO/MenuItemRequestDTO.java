package com.fooddelivery.DTO;

import com.fooddelivery.Entities.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MenuItemRequestDTO {
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    private double calories;

    public MenuItem toEntity() {

        MenuItem item = new MenuItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setAvailable(isAvailable);
        item.setVegetarian(isVegetarian);
        item.setCalories(calories);

        return item;
    }

    public void applyTo(MenuItem item) {

        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setAvailable(isAvailable);
        item.setVegetarian(isVegetarian);
        item.setCalories(calories);
    }
}
