package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.config.TestConfig;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TruckServiceImplUnitTests {

    @Mock
    private GenericDAO<Truck> genericDAO;
    @Mock
    private TruckDAO truckDAO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private GeoService geoService;
    @Mock
    private DepotService depotService;

    @InjectMocks
    private TruckServiceImpl truckService;

    private List<TruckDTO> dtoList = new ArrayList<>();
    private List<Truck> entityList = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(depotService.getDepotById(1L)).thenReturn(new Depot(1L));
        when(depotService.getDepotById(2L)).thenReturn(new Depot(2L));
        when(depotService.getDepotById(3L)).thenReturn(new Depot(3L));
        when(depotService.getDepotById(4L)).thenReturn(new Depot(4L));

        GHGeocodingEntry corp1 = new GHGeocodingEntry();
        corp1.setCountry("Austria");
        corp1.setState("Zalzburg");
        corp1.setCity("Krispl");
        GHGeocodingEntry corp2 = new GHGeocodingEntry();
        corp2.setCountry("Germany");
        corp2.setState("Bavaria");
        corp2.setCity("Regensburg");

        GHGeocodingEntry client1 = new GHGeocodingEntry();
        client1.setCountry("Italy");
        client1.setState("Trentino-Alto");
        client1.setCity("Trento");
        GHGeocodingEntry client2 = new GHGeocodingEntry();
        client2.setCountry("Hungary");
        client2.setState("Central Hungary");
        client2.setCity("Budapest");

        dtoList.add(TruckDTO.builder().id(1L)
                .plate("SL08923").capacity(1000).status(TruckStatus.OK)
                .coords("47.7162135,13.1801681").build());
        dtoList.add(TruckDTO.builder()
                .plate("AU12345").capacity(2000).status(TruckStatus.DEFECTIVE)
                .coords("46.0944323,11.1157568").build());

        entityList.add(Truck.builder().id(1L)
                .plate("SL08923").capacity(1000).status(TruckStatus.OK)
                .location(corp1).build());
        entityList.add(Truck.builder()
                .plate("AU12345").capacity(2000).status(TruckStatus.DEFECTIVE)
                .location(client1).build());
    }

    @Test
    public void truckSaved() {
        TruckDTO truckDTO = dtoList.get(0);
        Truck truckEntity = entityList.get(0);

        when(modelMapper.map(truckDTO, Truck.class)).thenReturn(truckEntity);

        truckService.saveTruck(truckDTO);

        verify(genericDAO, times(1)).create(truckEntity);
    }

    @Test
    public void truckUpdated() {
        TruckDTO truckDTO = dtoList.get(0);
        Truck truckEntity = entityList.get(0);

        when(modelMapper.map(truckDTO, Truck.class)).thenReturn(truckEntity);

        truckService.updateTruck(truckDTO);

        verify(genericDAO, times(1)).update(truckEntity);
    }

}
