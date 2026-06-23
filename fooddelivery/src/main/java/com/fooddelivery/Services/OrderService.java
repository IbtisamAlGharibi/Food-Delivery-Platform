package com.fooddelivery.Services;

import com.fooddelivery.DTO.RequestDTOs.OrderItemRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.OrderResponseDTO;
import com.fooddelivery.Entities.Customer;
import com.fooddelivery.Entities.Order;
import com.fooddelivery.Entities.Restaurant;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.CustomerRepository;
import com.fooddelivery.Repositories.OrderRepository;
import com.fooddelivery.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    RestaurantRepository restaurantRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository,CustomerRepository customerRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository= restaurantRepository;
    }
    public OrderResponseDTO createOrder(Integer customerId, Integer restaurantId, List<OrderItemRequestDTO> items){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order = orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
    public OrderResponseDTO createOrder(Integer customerId, Integer restaurantId,
                                        List<OrderItemRequestDTO> items, String notes){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setDeliveryNotes(notes);
        order = orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
}
