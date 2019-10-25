package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDAO cargoDAO;

    private GenericDAO<Cargo> dao;

    @Autowired
    public void setDao(GenericDAO<Cargo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cargo.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepotService depotService;

    @Override
    public List<CargoDTO> getByDepotId(Long id) {
        List<Cargo> cargoes = cargoDAO.findByDepotId(id);
        return cargoes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CargoDTO> getAllCargoes() {
        List<Cargo> cargoes = dao.findAll();
        return cargoes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveCargo(CargoDTO cargoDTO) {
        Cargo cargo = convertToEntity(cargoDTO);
        dao.create(cargo);
    }

    @Override
    public void updateCargo(CargoDTO cargoDTO) {
        Cargo cargo = convertToEntity(cargoDTO);
        dao.update(cargo);
    }

    @Override
    public CargoDTO getCargo(Long id) {
        Cargo cargo = dao.findOne(id);
        return convertToDto(cargo);
    }

    @Override
    public void deleteCargo(Long id) {
        dao.deleteById(id);
    }

    @Override
    public CargoDTO convertToDto(Cargo entity) {
        return modelMapper.map(entity, CargoDTO.class);
    }

    @Override
    public Cargo convertToEntity(CargoDTO dto) {
        Cargo cargo = modelMapper.map(dto, Cargo.class);

        long start = dto.getLocDepotId();
        cargo.setLocation(depotService.getDepot(start));
        long end = dto.getDestDepotId();
        cargo.setDestination(depotService.getDepot(end));

        return cargo;
    }

}
