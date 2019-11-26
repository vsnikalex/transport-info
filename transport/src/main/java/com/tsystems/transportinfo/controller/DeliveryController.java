package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.aspect.DeliveryEvent;
import com.tsystems.transportinfo.aspect.DriverEvent;
import com.tsystems.transportinfo.aspect.TruckEvent;
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
    @DeliveryEvent
    @DriverEvent
    @TruckEvent
    public void saveDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        log.info("Save delivery with cargo id={}, driver id={}, truck id={}",
                deliveryDTO.getCargoIDs(), deliveryDTO.getDriverIDs(), deliveryDTO.getTruckID());

        log.info("Estimated work hours: {}", deliveryDTO.getEstWorkHours());

        deliveryService.createDelivery(deliveryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDelivery(@PathVariable long id) {
        log.info("Delete delivery with id={}", id);
        deliveryService.deleteDelivery(id);
    }

    @PutMapping("/finish/{id}")
    @DeliveryEvent
    @DriverEvent
    @TruckEvent
    public boolean finishDelivery(@PathVariable long id) {
        log.info("Finish delivery with id={}", id);
        return deliveryService.finishDelivery(id);
    }

}
