package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Customer;
import com.fooddelivery.Entities.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressResponseDTO {
    private int id;
    private String street;
    private String city;
    private String building;
    private boolean isDefault;
    private boolean  isActive;

    public static CustomerAddressResponseDTO fromEntity(CustomerAddress customerAddress){
        CustomerAddressResponseDTO dto = new CustomerAddressResponseDTO();
        dto.setId(customerAddress.getId());
        dto.setStreet(customerAddress.getStreet());
        dto.setCity(customerAddress.getCity());
        dto.setBuilding(customerAddress.getBuilding());
        dto.setDefault(customerAddress.isDefault());
        dto.setActive(customerAddress.isActive());

        return dto;
    }

}
