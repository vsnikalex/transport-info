package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.config.TestConfig;
import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
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

import static org.mockito.Mockito.*;

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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(depotService.getDepotById(1L)).thenReturn(new Depot(1L));
        when(depotService.getDepotById(2L)).thenReturn(new Depot(2L));
    }

    @Test
    public void cargoSaved() {
        CargoDTO cargo = new CargoDTO();
        cargo.setDescription("IKEA Sofa");
        cargo.setWeight(150);
        cargo.setStatus(CargoStatus.PREPARED);
        cargo.setLocDepotId(1L);
        cargo.setDestDepotId(2L);

        Cargo cargoEntity = new Cargo();
        cargoEntity.setStartDepot(new Depot(1L));
        cargoEntity.setEndDepot(new Depot(2L));
        when(modelMapper.map(cargo, Cargo.class)).thenReturn(cargoEntity);

        cargoService.saveCargo(cargo);

        verify(genericDAO, times(1)).create(cargoEntity);
    }

}
