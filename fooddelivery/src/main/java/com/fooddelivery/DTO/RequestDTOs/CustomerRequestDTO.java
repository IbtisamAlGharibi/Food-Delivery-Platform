package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
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
    private int loyaltyPoints;

    public Customer toEntity() {
        Customer customer =new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPasswordHash(passwordHash);
        customer.setLoyaltyPoints(loyaltyPoints);
        return customer;
    }

    public void applyTo(Customer customer){
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPasswordHash(passwordHash);
        customer.setLoyaltyPoints(loyaltyPoints);
    }
}
