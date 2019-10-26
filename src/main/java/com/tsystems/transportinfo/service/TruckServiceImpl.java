package com.tsystems.transportinfo.service;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Truck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TruckServiceImpl implements TruckService {

    private GenericDAO<Truck> dao;

    @Autowired
    public void setDao(GenericDAO<Truck> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Truck.class);
    }

    @Autowired
    private TruckDAO truckDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GraphHopperService graphHopperService;

    @Override
    public List<TruckDTO> getNearestTrucks(GHPoint destination, long maxTravelTime) {
        List<Truck> trucks = truckDAO.findNearestTrucks(destination, maxTravelTime);
        return trucks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TruckDTO> getAllTrucks() {
        List<Truck> trucks = dao.findAll();
        return trucks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveTruck(TruckDTO truckDTO) {
        Truck truck = convertToEntity(truckDTO);
        dao.create(truck);
    }

    @Override
    public void updateTruck(TruckDTO truckDTO) {
        Truck truck = convertToEntity(truckDTO);
        dao.update(truck);
    }

    @Override
    public TruckDTO getTruck(Long id) {
        Truck truck = dao.findOne(id);
        return convertToDto(truck);
    }

    @Override
    public void deleteTruck(Long id) {
        dao.findOne(id);
    }

    @Override
    public TruckDTO convertToDto(Truck entity) {
        TruckDTO truckDTO = modelMapper.map(entity, TruckDTO.class);

        truckDTO.setDriversCnt(entity.getDelivery());

        return truckDTO;
    }

    @Override
    public Truck convertToEntity(TruckDTO dto) {
        Truck truck = modelMapper.map(dto, Truck.class);

        String coords = dto.getCoords();
        truck.setLocation(graphHopperService.coordsToEntry(coords));

        return truck;
    }

}
