package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.aspect.DeliveryEvent;
import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
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

    /**
     * Requests cargoes from {@link CargoDAO}
     * which are stored in a certain depot,
     * TODO: which are not marked as deleted.
     *
     * Filters off cargoes assigned to a delivery.
     *
     * @param id    {@link com.tsystems.transportinfo.data.entity.Depot} id
     * @return      list of {@link CargoDTO}
     */
    @Override
    @Transactional
    public List<CargoDTO> getAvailableByDepotId(Long id) {
        log.info("Request all available Cargoes at Depot id={} from DAO", id);
        List<Cargo> cargoes = cargoDAO.findByDepotId(id);
        return cargoes.stream()
                .filter(cargo -> cargo.getDelivery() == null)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Requests cargoes from {@link CargoDAO},
     * TODO: which are not marked as deleted.
     *
     * @return      list of {@link CargoDTO}
     */
    @Override
    @Transactional
    public List<CargoDTO> getAllCargoes() {
        log.info("Request all Cargoes from DAO");
        List<Cargo> cargoes = dao.findAll();
        return cargoes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * TODO: Marks cargoes as deleted.
     *
     */
    @Override
    @Transactional
    public void deleteCargo(Long id) {
        log.info("Delete Cargo id={}", id);
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public void saveCargo(CargoDTO cargoDTO) {
        log.info("Save Cargo");
        Cargo cargo = convertToEntity(cargoDTO);
        dao.create(cargo);
    }

    /**
     * Marked as {@link DeliveryEvent} to trigger
     * notification send after update.
     *
     */
    @Override
    @Transactional
    public void updateCargo(CargoDTO cargoDTO) {
        log.info("Update Cargo id={}", cargoDTO.getId());
        Cargo cargo = convertToEntity(cargoDTO);
        dao.update(cargo);
    }

    /**
     * Used to reflect load/unload operations during delivery.
     *
     */
    @Override
    @Transactional
    public void updateCargoStatus(long id, CargoStatus status) {
        log.info("Change Cargo id={} status to {}", id, status);
        cargoDAO.updateCargoStatus(id, status);
    }

    @Override
    @Transactional
    public CargoDTO getCargo(Long id) {
        log.info("Request Cargo id={} from DAO", id);
        Cargo cargo = dao.findOne(id);
        return convertToDto(cargo);
    }

    @Override
    public CargoDTO convertToDto(Cargo entity) {
        return modelMapper.map(entity, CargoDTO.class);
    }

    /**
     * Requests depot entities from {@link DepotService},
     * thus checking whether data is not fake.
     *
     */
    @Override
    public Cargo convertToEntity(CargoDTO dto) {
        Cargo cargo = modelMapper.map(dto, Cargo.class);

        long start = dto.getStartDepotId();
        cargo.setStartDepot(depotService.getDepotById(start));
        long end = dto.getEndDepotId();
        cargo.setEndDepot(depotService.getDepotById(end));

        return cargo;
    }

}
