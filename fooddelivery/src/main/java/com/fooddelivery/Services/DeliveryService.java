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
import com.fooddelivery.Utils.HelperUtils;
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
        DeliveryDriver driver = deliveryDriverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

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
    public List<DeliveryDriverResponseDTO> getAllDrivers() {
        List<DeliveryDriver> drivers = deliveryDriverRepository.getAllDrivers();
        List<DeliveryDriverResponseDTO> result = new ArrayList<>();
        for (DeliveryDriver driver : drivers) {
            result.add(DeliveryDriverResponseDTO.fromEntity(driver));
        }
        return result;
    }
    public List<DeliveryDriverResponseDTO> getOnlineDrivers() {
        List<DeliveryDriver> drivers = deliveryDriverRepository.getOnlineDrivers();
        List<DeliveryDriverResponseDTO> result = new ArrayList<>();
        for (DeliveryDriver driver : drivers) {
            result.add(DeliveryDriverResponseDTO.fromEntity(driver));
        }
        return result;
    }
    public DeliveryResponseDTO getDeliveryById(Integer id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found"));
        return DeliveryResponseDTO.fromEntity(delivery);
    }
    public List<DeliveryResponseDTO> getDeliveriesByStatus(String status) {
        List<Delivery> deliveries = deliveryRepository.findDeliveryByStatus(status);
        List<DeliveryResponseDTO> result = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            result.add(DeliveryResponseDTO.fromEntity(delivery));
        }
        return result;
    }
    public List<DeliveryDriverResponseDTO> getDriverLeaderboard() {
        List<DeliveryDriver> drivers = deliveryDriverRepository.getDriverLeaderboard();
        List<DeliveryDriverResponseDTO> result = new ArrayList<>();
        int limit = Math.min(10, drivers.size());

        for (int i = 0; i <= limit; i++) {
            result.add(DeliveryDriverResponseDTO.fromEntity(drivers.get(i)));
        }
        return result;
    }
    public List<DeliveryDriverResponseDTO> getNearbyDrivers(double lat, double lng, double radiusKm) {
        List<DeliveryDriver> drivers = deliveryDriverRepository.getOnlineDrivers();
        List<DeliveryDriverResponseDTO> result = new ArrayList<>();

        for(DeliveryDriver driver : drivers){
            double driverLat = Double.parseDouble(driver.getCurrentLat());
            double driverLng = Double.parseDouble(driver.getCurrentLng());
            double distance = HelperUtils.calculateDistance(lat, lng, driverLat, driverLng);
            if(distance <= radiusKm){
                result.add(DeliveryDriverResponseDTO.fromEntity(driver));
            }
        }
        return result;
    }
}
