package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.entity.Driver;

import java.util.List;

public interface DriverService {

    List<DriverDTO> getAvailableDrivers(GHGeocodingEntry city);

    List<DriverDTO> getAllDrivers();

    void saveDriver(DriverDTO driverDTO);

    void updateDriver(DriverDTO driverDTO);

    DriverDTO getDriver(Long id);

    void deleteDriver(Long id);

    DriverDTO convertToDto(Driver entity);

    Driver convertToEntity(DriverDTO dto);

}
