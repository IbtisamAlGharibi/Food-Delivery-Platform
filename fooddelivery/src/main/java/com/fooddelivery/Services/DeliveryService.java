package com.fooddelivery.Services;

import com.fooddelivery.DTO.ResponseDTOs.DeliveryResponseDTO;
import com.fooddelivery.Entities.Delivery;
import com.fooddelivery.Entities.DeliveryDriver;
import com.fooddelivery.Entities.Order;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.DeliveryRepository;
import com.fooddelivery.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;
    OrderRepository orderRepository;
    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
    }
    public DeliveryResponseDTO assignDriverToOrder(Integer orderId, Integer driverId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        DeliveryDriver driver = deliveryRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found")).getDeliveryDriver();

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setDeliveryDriver(driver);
        delivery.setStatus("ASSIGNED");
        delivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(delivery);
    }
}
