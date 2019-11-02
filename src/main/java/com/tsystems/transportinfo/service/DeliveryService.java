package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.entity.Delivery;

public interface DeliveryService {

    void createDelivery(DeliveryDTO deliveryDTO);

    void deleteDelivery(Long id);

    Delivery convertToEntity(DeliveryDTO dto);

    DeliveryDTO convertToDTO(Delivery entity);

}
