package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.ComboMeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboMealRequestDTO {
    private String comboName;
    private String description;
    private double totalPrice;
    private boolean isAvailable;

    public ComboMeal toEntity() {
        ComboMeal comboMeal = new ComboMeal();

        comboMeal.setComboName(comboName);
        comboMeal.setDescription(description);
        comboMeal.setTotalPrice(totalPrice);
        comboMeal.setAvailable(isAvailable);

        return comboMeal;
    }

    public void applyTo(ComboMeal comboMeal) {
        comboMeal.setComboName(comboName);
        comboMeal.setDescription(description);
        comboMeal.setTotalPrice(totalPrice);
        comboMeal.setAvailable(isAvailable);
    }
}
