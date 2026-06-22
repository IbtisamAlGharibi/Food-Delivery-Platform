package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponseDTO {

    private int id;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    private double calories;
    private Date createdDate;
    private Date updatedDate;


    public static MenuItemResponseDTO fromEntity(MenuItem item) {

        MenuItemResponseDTO dto = new MenuItemResponseDTO();

        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setAvailable(item.isAvailable());
        dto.setVegetarian(item.isVegetarian());
        dto.setCalories(item.getCalories());
        dto.setCreatedDate(item.getCreatedDate());
        dto.setUpdatedDate(item.getUpdatedDate());

        return dto;
    }
}
