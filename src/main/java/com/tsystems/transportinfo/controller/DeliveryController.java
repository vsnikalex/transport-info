package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/add")
    public void saveDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.createDelivery(deliveryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDelivery(@PathVariable long id) {
        deliveryService.deleteDelivery(id);
    }

}
