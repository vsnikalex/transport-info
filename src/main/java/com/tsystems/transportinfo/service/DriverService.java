package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.entity.Driver;

import java.util.List;

public interface DriverService {

    public List<DriverDTO> getAllDrivers();

    public void saveDriver(DriverDTO driverDTO);

    public void updateDriver(DriverDTO driverDTO);

    public DriverDTO getDriver(Long id);

    public void deleteDriver(Long id);

    public DriverDTO convertToDto(Driver entity);

    public Driver convertToEntity(DriverDTO dto);

}
