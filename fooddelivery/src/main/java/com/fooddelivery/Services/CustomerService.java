package com.fooddelivery.Services;

import com.fooddelivery.DTO.RequestDTOs.CustomerAddressRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.CustomerRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.CustomerAddressResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.CustomerResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.OrderResponseDTO;
import com.fooddelivery.Entities.Customer;
import com.fooddelivery.Entities.CustomerAddress;
import com.fooddelivery.Entities.Order;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.CustomerAddressRepository;
import com.fooddelivery.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerAddressRepository customerAddressRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto){
        Customer customer = dto.toEntity();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPasswordHash());
        customer= customerRepository.save(customer);
        return CustomerResponseDTO.fromEntity(customer);
    }
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO initialAddress) {
        Customer customer = dto.toEntity();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPasswordHash());
        customer.setLoyaltyPoints(dto.getLoyaltyPoints());

        CustomerAddress customerAddress = initialAddress.toEntity();
        customerAddress.setStreet(initialAddress.getStreet());
        customerAddress.setCity(initialAddress.getCity());
        customerAddress.setBuilding(initialAddress.getBuilding());
        customerAddress.setDefault(initialAddress.isDefault());
        customerAddress = customerAddressRepository.save(customerAddress);
        customer.getCustomerAddressList().add(customerAddress);
        customer = customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }

    public CustomerAddressResponseDTO addAddress(Integer customerId,CustomerAddressRequestDTO address) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        CustomerAddress customerAddress = address.toEntity();
        customerAddress.setStreet(address.getStreet());
        customerAddress.setCity(address.getCity());
        customerAddress.setBuilding(address.getBuilding());
        customerAddress.setDefault(address.isDefault());
        customerAddress = customerAddressRepository.save(customerAddress);
        customer.getCustomerAddressList().add(customerAddress);
        customerRepository.save(customer);

        return CustomerAddressResponseDTO.fromEntity(customerAddress);
    }
    public CustomerResponseDTO updateLoyaltyPoints(Integer customerId, int points){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
        customer = customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }
    public CustomerResponseDTO applyLoyaltyPenalty(Integer customerId, int pointsDeducted){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() - pointsDeducted);
        customer = customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }
    public CustomerResponseDTO deactivateCustomer(Integer customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setActive(false);
        customer = customerRepository.save(customer);

        return CustomerResponseDTO.fromEntity(customer);
    }
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
    public CustomerResponseDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return CustomerResponseDTO.fromEntity(customer);
    }
    public List<CustomerAddressResponseDTO> getAllCustomerAddresses(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        List<CustomerAddressResponseDTO> addressResponseList = new ArrayList<>();
        for (CustomerAddress address : customer.getCustomerAddressList()) {
            CustomerAddressResponseDTO dto = CustomerAddressResponseDTO.fromEntity(address);
            addressResponseList.add(dto);
        }
        return addressResponseList;
    }
    public CustomerAddressResponseDTO setDefaultAddress(Integer addressId) {
        CustomerAddress address = customerAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        address.setDefault(true);
        customerAddressRepository.save(address);
        return CustomerAddressResponseDTO.fromEntity(address);
    }

    public void deleteAddress(Integer addressId) {
        CustomerAddress address = customerAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        address.setActive(false);
        customerAddressRepository.save(address);
    }
    public CustomerResponseDTO getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return CustomerResponseDTO.fromEntity(customer);
    }
    public List<OrderResponseDTO> getCustomerOrders(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<OrderResponseDTO> orderResponseList = new ArrayList<>();
        for (Order order : customer.getOrderList()) {
            OrderResponseDTO dto = OrderResponseDTO.fromEntity(order);
            orderResponseList.add(dto);
        }
        return orderResponseList;
    }
    public CustomerResponseDTO getTopLoyalCustomers( ) {
      return (CustomerResponseDTO) customerRepository.getTopLoyalCustomers();
    }
    public Page<CustomerResponseDTO> searchCustomers(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findByFirstName(name,pageable).map(CustomerResponseDTO::fromEntity);
    }
}
