package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.aspect.DriverEvent;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.data.entity.Truck;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private GenericDAO<Driver> dao;

    @Autowired
    public void setDao(GenericDAO<Driver> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Driver.class);
    }

    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GeoService geoService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DeliveryService deliveryService;

    @Override
    public List<DriverDTO> getAvailableDrivers(GHGeocodingEntry city) {
        log.info("Request all available Drivers in {} from DAO", city.getCity());
        List<Driver> drivers = driverDAO.findAvailableDrivers(city);
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        log.info("Request all Drivers from DAO");

        List<Driver> drivers = dao.findAll();
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @DriverEvent
    public void saveDriver(DriverDTO driverDTO) {
        log.info("Save Driver");
        Driver driver = convertToEntity(driverDTO);
        dao.create(driver);
    }

    @Override
    @DriverEvent
    public void updateDriver(DriverDTO driverDTO) {
        log.info("Update Driver id={}", driverDTO.getId());
        Driver driver = convertToEntity(driverDTO);
        dao.update(driver);
    }

    @Override
    public DriverDTO getDriver(Long id) {
        log.info("Request Driver id={} from DAO", id);
        Driver driver = dao.findOne(id);
        return convertToDto(driver);
    }

    @Override
    @DriverEvent
    public void deleteDriver(Long id) {
        log.info("Delete Driver id={}", id);
        dao.deleteById(id);
    }

    @Override
    public DriverDTO convertToDto(Driver entity) {
        DriverDTO driverDTO = modelMapper.map(entity, DriverDTO.class);

        driverDTO.setWorkedThisMonth(entity.getTasks());
        driverDTO.setStatus(entity.getTasks());

        Delivery driverDelivery = entity.getDelivery();
        if (driverDelivery != null) {
            Truck deliveryTruck = driverDelivery.getTruck();
            TruckDTO truckDTO = truckService.convertToDto(deliveryTruck);
            driverDTO.setTruckDTO(truckDTO);

            // TODO: add co-workers List<SecuredDriverDTO>
            DeliveryDTO deliveryDTO = deliveryService.convertToDTO(driverDelivery);
            driverDTO.setDeliveryDTO(deliveryDTO);
        }

        return driverDTO;
    }

    @Override
    public Driver convertToEntity(DriverDTO dto) {
        Driver driver = modelMapper.map(dto, Driver.class);

        String coords = dto.getCoords();
        driver.setLocation(geoService.coordsToEntry(coords));

        return driver;
    }

}
