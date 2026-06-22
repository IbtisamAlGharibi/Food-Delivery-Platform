package com.fooddelivery.DTO;

import com.fooddelivery.Entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDTO {
    private int customerCode;
    private String firstName;
    private String lastName;

    public static CustomerSummaryDTO fromEntity(Customer customer){
        CustomerSummaryDTO dto = new CustomerSummaryDTO();
        dto.setCustomerCode(customer.getCustomerCode());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());

        return dto;
    }
}
