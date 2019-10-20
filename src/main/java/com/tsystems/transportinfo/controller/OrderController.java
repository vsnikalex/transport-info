package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.service.DeliveryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/all")
    public List<DeliveryDTO> allDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        return deliveries.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable long id) {
        deliveryService.deleteDelivery(id);
    }

    private DeliveryDTO convertToDto(Delivery delivery) {
        DeliveryDTO deliveryDTO = modelMapper.map(delivery, DeliveryDTO.class);

        deliveryDTO.setCargo(modelMapper.map(delivery.getCargo(), CargoDTO.class));
        deliveryDTO.setTruck(modelMapper.map(delivery.getTruck(), TruckDTO.class));
//        deliveryDTO.setWorkingDrivers(delivery.getTasks());

        return deliveryDTO;
    }

}
