package com.fooddelivery.DTO.SummaryDTOs;

import com.fooddelivery.Entities.ComboMeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboMealSummaryDTO {
    private int id;
    private String comboName;
    private double totalPrice;
    private boolean isAvailable;

    public static ComboMealSummaryDTO fromEntity(ComboMeal comboMeal) {

        ComboMealSummaryDTO dto = new ComboMealSummaryDTO();
        dto.setId(comboMeal.getId());
        dto.setComboName(comboMeal.getComboName());
        dto.setTotalPrice(comboMeal.getTotalPrice());
        dto.setAvailable(comboMeal.isAvailable());

        return dto;
    }
}
