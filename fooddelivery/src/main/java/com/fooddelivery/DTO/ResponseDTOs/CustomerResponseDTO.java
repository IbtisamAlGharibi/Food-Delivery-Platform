package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private int customerCode;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private int loyaltyPoints;
    private boolean  isActive;

    public static CustomerResponseDTO fromEntity(Customer customer){
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setCustomerCode(customer.getCustomerCode());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setLoyaltyPoints(customer.getLoyaltyPoints());
        dto.setActive(customer.isActive());

        return dto;
    }

}
