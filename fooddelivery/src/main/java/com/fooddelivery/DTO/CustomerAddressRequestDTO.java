package com.fooddelivery.DTO;

import com.fooddelivery.Entities.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressRequestDTO {
    private String street;
    private String city;
    private String building;
    private boolean isDefault;

    public CustomerAddress toEntity() {
        CustomerAddress customerAddress = new CustomerAddress();
       customerAddress.setStreet(street);
       customerAddress.setCity(city);
       customerAddress.setBuilding(building);
       customerAddress.setDefault(isDefault);
       return customerAddress;
    }

    public void applyTo(CustomerAddress customerAddress){
        customerAddress.setStreet(street);
        customerAddress.setCity(city);
        customerAddress.setBuilding(building);
        customerAddress.setDefault(isDefault);
    }
}
