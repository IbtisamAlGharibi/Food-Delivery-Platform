package com.fooddelivery.Services;

import com.fooddelivery.DTO.RequestDTOs.DeliveryDriverRequestDTO;
import com.fooddelivery.DTO.ResponseDTOs.DeliveryDriverResponseDTO;
import com.fooddelivery.DTO.ResponseDTOs.DeliveryResponseDTO;
import com.fooddelivery.Entities.Delivery;
import com.fooddelivery.Entities.DeliveryDriver;
import com.fooddelivery.Entities.Order;
import com.fooddelivery.Exceptions.ResourceNotFoundException;
import com.fooddelivery.Repositories.DeliveryDriverRepository;
import com.fooddelivery.Repositories.DeliveryRepository;
import com.fooddelivery.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;
    OrderRepository orderRepository;
    DeliveryDriverRepository deliveryDriverRepository;
    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository,DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.deliveryDriverRepository=deliveryDriverRepository;
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
    public DeliveryResponseDTO autoAssignDriver(Integer orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        List<Delivery> deliveries = deliveryRepository.findAll();

        DeliveryDriver driver = new DeliveryDriver();
        for(Delivery delivery : deliveries){
            if(delivery.getDeliveryDriver() != null &&
                    delivery.getDeliveryDriver().isOnline()){
                driver = delivery.getDeliveryDriver();
                break;
            }
        }
        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setDeliveryDriver(driver);
        delivery.setStatus("ASSIGNED");
        delivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(delivery);
    }
    public DeliveryDriverResponseDTO updateDriverLocation(Integer driverId, double lat, double lng){
        List<Delivery> deliveries = deliveryRepository.findAll();
        for(Delivery delivery : deliveries){
            if(delivery.getDeliveryDriver() != null &&
                    delivery.getDeliveryDriver().getDriverCode() == driverId){
                DeliveryDriver driver = delivery.getDeliveryDriver();
                driver.setCurrentLat(String.valueOf(lat));
                driver.setCurrentLng(String.valueOf(lng));
                deliveryRepository.save(delivery);
                return DeliveryDriverResponseDTO.fromEntity(driver);
            }
        }
        throw new ResourceNotFoundException("Driver not found");
    }
    public DeliveryResponseDTO markDeliveryPickedUp(Integer deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found"));
        delivery.setStatus("PICKED UP");
        delivery.setPickedUpAt(LocalDateTime.now());
        delivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(delivery);
    }
    public DeliveryResponseDTO markDeliveryDelivered(Integer deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found"));
        delivery.setStatus("DELIVERED");
        delivery.setDeliveredAt(LocalDateTime.now());
        delivery = deliveryRepository.save(delivery);

        return DeliveryResponseDTO.fromEntity(delivery);
    }
    public List<DeliveryResponseDTO> getDeliveriesForDriver(Integer driverId, String status){
        List<Delivery> deliveries = deliveryRepository.findByDeliveryDriverIdAndStatus(driverId, status);
        List<DeliveryResponseDTO> result = new ArrayList<>();

        for(Delivery delivery : deliveries){
            result.add(DeliveryResponseDTO.fromEntity(delivery));
        }
        return result;
    }
    public DeliveryDriverResponseDTO toggleDriverOnlineStatus(Integer driverId, boolean isOnline){
        List<Delivery> deliveries = deliveryRepository.findAll();
        for(Delivery delivery : deliveries){
            if(delivery.getDeliveryDriver() != null &&
                    delivery.getDeliveryDriver().getDriverCode() == driverId){
                DeliveryDriver driver = delivery.getDeliveryDriver();
                driver.setOnline(isOnline);
                deliveryRepository.save(delivery);
                return DeliveryDriverResponseDTO.fromEntity(driver);
            }
        }
        throw new ResourceNotFoundException("Driver not found");
    }
    public DeliveryDriverResponseDTO createDriver(DeliveryDriverRequestDTO dto) {
        DeliveryDriver driver = dto.toEntity();
        driver.setOnline(dto.isOnline());
        driver = deliveryDriverRepository.save(driver);
        return DeliveryDriverResponseDTO.fromEntity(driver);
    }
}
