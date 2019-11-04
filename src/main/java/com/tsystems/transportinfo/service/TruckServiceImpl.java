package com.tsystems.transportinfo.service;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.Truck;
import lombok.Synchronized;
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

    @Autowired
    private DepotService depotService;

    @Override
    @Synchronized
    public List<TruckDTO> getAvailableTrucks(long depotId, long maxTravelTime) {
        log.info("Request all available Trucks at Depot id={} " +
                    "with max travel time = {} (Unix timestamp) from DAO", depotId, maxTravelTime);

        Depot depot = depotService.getDepotById(depotId);
        GHPoint destPoint = graphHopperService.pointFromEntry(depot.getLocation());

        List<Truck> trucks = truckDAO.findAvailableTrucks(destPoint , maxTravelTime);
        return trucks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TruckDTO> getAllTrucks() {
        log.info("Request all Trucks from DAO");
        List<Truck> trucks = dao.findAll();
        return trucks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveTruck(TruckDTO truckDTO) {
        log.info("Save Truck");
        Truck truck = convertToEntity(truckDTO);
        dao.create(truck);
    }

    @Override
    public void updateTruck(TruckDTO truckDTO) {
        log.info("Update Truck id={}", truckDTO.getId());
        Truck truck = convertToEntity(truckDTO);
        dao.update(truck);
    }

    @Override
    public TruckDTO getTruck(Long id) {
        log.info("Request Truck id={} from DAO", id);
        Truck truck = dao.findOne(id);
        return convertToDto(truck);
    }

    @Override
    public void deleteTruck(Long id) {
        log.info("Delete Truck id={}", id);
        dao.findOne(id);
    }

    @Override
    public TruckDTO convertToDto(Truck entity) {
        log.info("Convert Truck id={} entity to TruckDTO", entity.getId());
        TruckDTO truckDTO = modelMapper.map(entity, TruckDTO.class);

        truckDTO.setDriversCnt(entity.getDelivery());

        return truckDTO;
    }

    @Override
    public Truck convertToEntity(TruckDTO dto) {
        log.info("Convert TruckDTO ({}) to Truck entity", dto.getPlate());
        Truck truck = modelMapper.map(dto, Truck.class);

        String coords = dto.getCoords();
        truck.setLocation(graphHopperService.coordsToEntry(coords));

        return truck;
    }

}
