package com.fooddelivery.DTO;

import com.fooddelivery.Entities.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressSummaryDTO {
    private int id;
    private String street;
    private String city;
    private String building;

    public static CustomerAddressResponseDTO fromEntity(CustomerAddress customerAddress){
        CustomerAddressResponseDTO dto = new CustomerAddressResponseDTO();
        dto.setId(customerAddress.getId());
        dto.setStreet(customerAddress.getStreet());
        dto.setCity(customerAddress.getCity());
        dto.setBuilding(customerAddress.getBuilding());
        dto.setActive(customerAddress.isActive());

        return dto;
    }
}
