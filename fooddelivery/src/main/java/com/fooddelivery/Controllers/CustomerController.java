package com.fooddelivery.Controllers;

import com.fooddelivery.DTO.RequestDTOs.CustomerAddressRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.CustomerRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.CustomerAddressResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.CustomerResponseDTO;
import com.fooddelivery.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO customer = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<CustomerResponseDTO> deactivateCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.deactivateCustomer(id));
    }
    @PutMapping("/{id}/loyalty/add/{points}")
    public ResponseEntity<CustomerResponseDTO> addLoyaltyPoints(@PathVariable Integer id, @PathVariable int points) {
        return ResponseEntity.ok(customerService.updateLoyaltyPoints(id, points));
    }
    @PutMapping("/{id}/loyalty/deduct/{points}")
    public ResponseEntity<CustomerResponseDTO> deductLoyaltyPoints(@PathVariable Integer id, @PathVariable int points) {
        return ResponseEntity.ok(customerService.applyLoyaltyPenalty(id, points));
    }
    @PostMapping("/{id}/addresses")
    public ResponseEntity<CustomerAddressResponseDTO> addAddress(@PathVariable Integer id,
            @Valid @RequestBody CustomerAddressRequestDTO dto) {
        CustomerAddressResponseDTO address = customerService.addAddress(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }
    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<CustomerAddressResponseDTO>> getCustomerAddresses(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getAllCustomerAddresses(id));
    }
}
