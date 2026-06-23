package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.RestaurantOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;

    public RestaurantOwner toEntity() {

        RestaurantOwner owner = new RestaurantOwner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setEmail(email);
        owner.setPhone(phone);
        owner.setPasswordHash(passwordHash);

        return owner;
    }

    public void applyTo(RestaurantOwner owner) {

        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setEmail(email);
        owner.setPhone(phone);
        owner.setPasswordHash(passwordHash);
    }
}
