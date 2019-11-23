package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.test.TestConfig;
import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CargoServiceImplUnitTests {

    @Mock
    private GenericDAO<Cargo> genericDAO;
    @Mock
    private CargoDAO cargoDAO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private DepotService depotService;

    @InjectMocks
    private CargoServiceImpl cargoService;

    private List<CargoDTO> dtoList = new ArrayList<>();
    private List<Cargo> entityList = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(depotService.getDepotById(1L)).thenReturn(new Depot(1L));
        when(depotService.getDepotById(2L)).thenReturn(new Depot(2L));
        when(depotService.getDepotById(3L)).thenReturn(new Depot(3L));
        when(depotService.getDepotById(4L)).thenReturn(new Depot(4L));

        dtoList.add(CargoDTO.builder().id(1L)
                .description("IKEA Sofa").status(CargoStatus.PREPARED)
                .weight(200).startDepotId(1L).endDepotId(2L).build());
        dtoList.add(CargoDTO.builder()
                .description("IKEA Table").status(CargoStatus.SHIPPED)
                .weight(100).startDepotId(1L).endDepotId(3L).build());
        dtoList.add(CargoDTO.builder()
                .description("Tesla Model X").status(CargoStatus.DELIVERED)
                .weight(2300).startDepotId(1L).endDepotId(4L).build());

        entityList.add(Cargo.builder().id(1L)
                .description("IKEA Sofa").status(CargoStatus.PREPARED)
                .weight(200).startDepot(new Depot(1L)).endDepot(new Depot(2L))
                .delivery(null).build());
        entityList.add(Cargo.builder()
                .description("IKEA Table").status(CargoStatus.SHIPPED)
                .weight(100).startDepot(new Depot(1L)).endDepot(new Depot(3L))
                .delivery(new Delivery(1L)).build());
        entityList.add(Cargo.builder()
                .description("IKEA Table").status(CargoStatus.DELIVERED)
                .weight(2300).startDepot(new Depot(1L)).endDepot(new Depot(4L))
                .delivery(new Delivery(2L)).build());
    }

    @Test
    public void cargoSaved() {
        CargoDTO cargoDTO = dtoList.get(0);
        Cargo cargoEntity = entityList.get(0);

        when(modelMapper.map(cargoDTO, Cargo.class)).thenReturn(cargoEntity);

        cargoService.saveCargo(cargoDTO);

        verify(genericDAO, times(1)).create(cargoEntity);
    }

    @Test
    public void cargoUpdated() {
        CargoDTO cargoDTO = dtoList.get(0);
        Cargo cargoEntity = entityList.get(0);

        when(modelMapper.map(cargoDTO, Cargo.class)).thenReturn(cargoEntity);

        cargoService.updateCargo(cargoDTO);

        verify(genericDAO, times(1)).update(cargoEntity);
    }

    @Test
    public void cargoStatusUpdated() {
        cargoService.updateCargoStatus(1L, CargoStatus.SHIPPED);

        verify(cargoDAO, times(1)).updateCargoStatus(1L, CargoStatus.SHIPPED);
    }

    @Test
    public void cargoFound() {
        Cargo cargoEntity = entityList.get(0);
        CargoDTO cargoDTO = dtoList.get(0);

        when(genericDAO.findOne(cargoEntity.getId())).thenReturn(cargoEntity);
        when(modelMapper.map(cargoEntity, CargoDTO.class)).thenReturn(cargoDTO);

        CargoDTO foundDTO = cargoService.getCargo(cargoEntity.getId());

        verify(genericDAO, times(1)).findOne(cargoEntity.getId());
        assertEquals(foundDTO, cargoDTO);
    }

}
