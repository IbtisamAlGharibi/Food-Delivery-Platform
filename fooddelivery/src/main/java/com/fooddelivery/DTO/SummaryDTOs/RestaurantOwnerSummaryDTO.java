package com.fooddelivery.DTO.SummaryDTOs;

import com.fooddelivery.Entities.RestaurantOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerSummaryDTO {
    private String businessLicenseCode;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;

    public static RestaurantOwnerSummaryDTO fromEntity(RestaurantOwner owner) {

        RestaurantOwnerSummaryDTO dto = new RestaurantOwnerSummaryDTO();
        dto.setBusinessLicenseCode(owner.getBusinessLicenseCode());
        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setEmail(owner.getEmail());
        dto.setActive(owner.isActive());

        return dto;
    }
}
