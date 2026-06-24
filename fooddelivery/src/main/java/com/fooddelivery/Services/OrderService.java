package com.fooddelivery.Services;
import com.fooddelivery.Repositories.CorporateOrderRepository;
import com.fooddelivery.DTO.RequestDTOs.CorporateOrderRequestDTO;
import com.fooddelivery.DTO.RequestDTOs.OrderItemRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.CorporateOrderResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.OrderResponseDTO;
import com.fooddelivery.Entities.*;
import com.fooddelivery.Exceptions.InvalidOrderStateException;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    RestaurantRepository restaurantRepository;
    MenuItemRepository menuItemRepository;
    OrderItemRepository orderItemRepository;
    CorporateOrderRepository corporateOrderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository,CustomerRepository customerRepository, RestaurantRepository restaurantRepository
    ,MenuItemRepository menuItemRepository, OrderItemRepository orderItemRepository, CorporateOrderRepository corporateOrderRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository= restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.corporateOrderRepository= corporateOrderRepository;
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
    public OrderResponseDTO addMenuItemToOrder(Integer orderId, Integer menuItemId, int quantity){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        OrderItem item = new OrderItem();
        item.setMenuItem(menuItem);
        item.setOrder(order);
        item.setQuantity(quantity);
        item.setUnitPrice(menuItem.getPrice());
        item.setTotalPrice(quantity * menuItem.getPrice());
        item = (OrderItem) orderItemRepository.save(item);
        order.getOrderItemList().add(item);
        orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
    public void removeMenuItemFromOrder(Integer orderId, Integer orderItemId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (order.getOrderItemList() != null) {
            for (OrderItem item : order.getOrderItemList()) {
                if (item.getItemCode() == orderItemId) {
                    item.setActive(false);
                    break;
                }
            }
        }
        orderRepository.save(order);
    }
    public OrderResponseDTO applyDiscount(Integer orderId, double discountAmount){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setDiscountAmount(discountAmount);
        order = orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
    public OrderResponseDTO updateOrderStatus(Integer orderId, String newStatus){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(newStatus);
        order = orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
    public OrderResponseDTO cancelOrder(Integer orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if(!order.getStatus().equalsIgnoreCase("PENDING")){
            throw new InvalidOrderStateException("Only pending orders can be cancelled");
        }
        order.setStatus("CANCELLED");
        order.setActive(false);
        order = orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
    public OrderResponseDTO calculateOrderTotals(Integer orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        double subtotal = 0;
        for(OrderItem item : order.getOrderItemList()){
            subtotal += item.getTotalPrice();
        }
        order.setSubtotal(subtotal);
        order.setTotalAmount((subtotal + order.getDeliveryFee()) - order.getDiscountAmount());
        order = orderRepository.save(order);

        return OrderResponseDTO.fromEntity(order);
    }
    public CorporateOrderResponseDTO placeCorporateOrder(CorporateOrderRequestDTO dto){
        CorporateOrder corporateOrder = dto.toEntity();
        corporateOrder.setCompanyName(dto.getCompanyName());
        corporateOrder.setCostCenter(dto.getCostCenter());
        corporateOrder.setStatus(dto.getStatus());
        corporateOrder.setTotalAmount(dto.getTotalAmount());
        corporateOrder = corporateOrderRepository.save(corporateOrder);

        return CorporateOrderResponseDTO.fromEntity(corporateOrder);
    }
    public OrderResponseDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return OrderResponseDTO.fromEntity(order);
    }
}
