package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.ComboMeal;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboMealResponseDTO {
    private int id;
    private String comboName;
    private String description;
    private double totalPrice;
    private boolean isAvailable;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive;

    public static ComboMealResponseDTO fromEntity(ComboMeal comboMeal) {

        ComboMealResponseDTO dto = new ComboMealResponseDTO();

        dto.setId(comboMeal.getId());
        dto.setComboName(comboMeal.getComboName());
        dto.setDescription(comboMeal.getDescription());
        dto.setTotalPrice(comboMeal.getTotalPrice());
        dto.setAvailable(comboMeal.isAvailable());
        dto.setCreatedDate(comboMeal.getCreatedDate());
        dto.setUpdatedDate(comboMeal.getUpdatedDate());
        dto.setActive(comboMeal.isActive());

        return dto;
    }
}
