package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/add")
    public void saveDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        log.info("Save delivery with cargo id={}, driver id={}, truck id={}",
                deliveryDTO.getCargoIDs(), deliveryDTO.getDriverIDs(), deliveryDTO.getTruckID());
        deliveryService.createDelivery(deliveryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDelivery(@PathVariable long id) {
        log.info("Delete delivery with id={}", id);
        deliveryService.deleteDelivery(id);
    }

}