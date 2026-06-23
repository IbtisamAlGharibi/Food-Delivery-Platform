package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.RestaurantOwner;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerRequestDTO {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Phone number must contain 8-15 digits")
    private int phone;
    @NotBlank(message = "Password is required")
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
