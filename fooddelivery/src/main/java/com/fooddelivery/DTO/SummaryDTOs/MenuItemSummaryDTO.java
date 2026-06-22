package com.fooddelivery.DTO.SummaryDTOs;

import com.fooddelivery.Entities.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemSummaryDTO {
    private int id;
    private String name;
    private double price;

    public static MenuItemSummaryDTO fromEntity(MenuItem item) {

        MenuItemSummaryDTO dto = new MenuItemSummaryDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setPrice(item.getPrice());

        return dto;
    }
}
