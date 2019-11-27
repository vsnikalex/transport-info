package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
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

    @Autowired
    private TaskService taskService;

    @Autowired
    private DepotService depotService;

    @Autowired
    private TransportUserDetailsService transportUserDetailsService;

    @Override
    @Transactional(readOnly = true)
    public List<DriverDTO> getAvailableDrivers(GHGeocodingEntry city) {
        log.info("Request all available Drivers in {} from DAO", city.getCity());
        List<Driver> drivers = driverDAO.findAvailableDrivers(city);
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverDTO> getAllDrivers() {
        log.info("Request all Drivers from DAO");

        List<Driver> drivers = dao.findAll();
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveDriver(DriverDTO driverDTO) {
        log.info("Save Driver");

        User user = modelMapper.map(driverDTO, User.class);
        transportUserDetailsService.saveUser(user);

        Driver driver = convertToEntity(driverDTO);
        dao.create(driver);
    }

    @Override
    @Transactional
    public void updateDriver(DriverDTO driverDTO) {
        log.info("Update Driver id={}", driverDTO.getId());
        Driver driver = convertToEntity(driverDTO);
        dao.update(driver);
    }

    @Override
    @Transactional
    public DriverDTO getDriver(Long id) {
        log.info("Request Driver id={} from DAO", id);
        Driver driver = dao.findOne(id);
        return convertToDto(driver);
    }

    @Override
    @Transactional
    public long getIdByUsername(String username) {
        return driverDAO.getIdByUsername(username);
    }

    /**
     * Underlying dao method returns false
     * if encounters business constraints or no Driver found.
     *
     */
    @Override
    @Transactional
    public boolean deleteDriver(Long id) {
        log.info("Attempt to Delete Driver id={}", id);
        return driverDAO.deleteDriver(id);
    }

    @Override
    public DriverDTO convertToDto(Driver entity) {
        DriverDTO driverDTO = modelMapper.map(entity, DriverDTO.class);

        double workedThisMonth = taskService.calculateWorkHours(entity.getTasks(), Instant.now().getEpochSecond());

        // USED IN DEMONSTRATION PURPOSES
        if (null != entity.getDelivery() && entity.getDelivery().isDone()) {
            workedThisMonth += entity.getDelivery().getEstWorkHours();
        }
        // MUST BE DELETED

        driverDTO.setWorkedThisMonth(workedThisMonth);
        driverDTO.setStatus(entity.getTasks());

        Delivery driverDelivery = entity.getDelivery();
        if (driverDelivery != null) {
            Truck deliveryTruck = driverDelivery.getTruck();
            TruckDTO truckDTO = truckService.convertToDto(deliveryTruck);
            driverDTO.setTruckDTO(truckDTO);

            DeliveryDTO deliveryDTO = deliveryService.convertToDTO(driverDelivery);
            driverDTO.setDeliveryDTO(deliveryDTO);
        }

        return driverDTO;
    }

    @Override
    public Driver convertToEntity(DriverDTO dto) {
        Driver driver = modelMapper.map(dto, Driver.class);

        String coords = dto.getCoords();
        Depot depot = depotService.getDepotByCoords(coords);
        driver.setLocation(depot.getLocation());

        return driver;
    }

}
