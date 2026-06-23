package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.RestaurantOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerResponseDTO {
    private String businessLicenseCode;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive;

    public static RestaurantOwnerResponseDTO fromEntity(RestaurantOwner owner) {

        RestaurantOwnerResponseDTO dto = new RestaurantOwnerResponseDTO();
        dto.setBusinessLicenseCode(owner.getBusinessLicenseCode());
        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setEmail(owner.getEmail());
        dto.setPhone(owner.getPhone());
        dto.setCreatedDate(owner.getCreatedDate());
        dto.setUpdatedDate(owner.getUpdatedDate());
        dto.setActive(owner.isActive());

        return dto;
    }
}
