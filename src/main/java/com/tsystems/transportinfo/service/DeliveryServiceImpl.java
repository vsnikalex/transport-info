package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Truck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private GenericDAO<Delivery> dao;

    @Autowired
    public void setDao(GenericDAO<Delivery> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Delivery.class);
    }

    @Autowired
    private CargoDAO cargoDAO;

    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createDelivery(DeliveryDTO deliveryDTO) {
        Truck truck = new Truck(deliveryDTO.getTruckID());

        Delivery delivery = new Delivery();
        delivery.setRoute(deliveryDTO.getRoute());
        delivery.setTruck(truck);

        dao.create(delivery);

        for (long cargoID : deliveryDTO.getCargoIDs()) {
            cargoDAO.assignToDelivery(cargoID, delivery.getId());
        }

        for (long driverId : deliveryDTO.getDriverIDs()) {
            driverDAO.assignToDelivery(driverId, delivery.getId());
        }
    }

    @Override
    public void deleteDelivery(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Delivery convertToEntity(DeliveryDTO dto) {
        return modelMapper.map(dto, Delivery.class);
    }

}
