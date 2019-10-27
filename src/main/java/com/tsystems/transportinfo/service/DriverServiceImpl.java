package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.data.entity.Truck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    private GraphHopperService graphHopperService;

    @Autowired
    private TruckService truckService;

    @Override
    public List<DriverDTO> getDriversByCity(GHGeocodingEntry city) {
        List<Driver> drivers = driverDAO.findDriversByCity(city);
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = dao.findAll();
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        dao.create(driver);
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        dao.update(driver);
    }

    @Override
    public DriverDTO getDriver(Long id) {
        Driver driver = dao.findOne(id);
        return convertToDto(driver);
    }

    @Override
    public void deleteDriver(Long id) {
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
            driverDTO.setTruck(truckDTO);
        }

        return driverDTO;
    }

    @Override
    public Driver convertToEntity(DriverDTO dto) {
        Driver driver = modelMapper.map(dto, Driver.class);

        String coords = dto.getCoords();
        driver.setLocation(graphHopperService.coordsToEntry(coords));

        return driver;
    }

}
