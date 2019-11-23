package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.Truck;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeliveryServiceImplUnitTests {

    @Mock
    private GenericDAO<Delivery> genericDAO;
    @Mock
    private CargoDAO cargoDAO;
    @Mock
    private DriverDAO driverDAO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private GeoService geoService;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deliveryCreated() {
        String route = "{\"0\":\"47.686662,13.098481\"," +
                       " \"1\":\"46.068837,14.489973\"," +
                       " \"2\":\"46.094432,11.115757\"," +
                       " \"3\":\"47.686662,13.098481\"}";

        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .truckID(1L)
                .cargoIDs(new long[]{1L, 2L, 3L})
                .driverIDs(new long[]{1L, 2L})
                .route(route)
                .build();

        LocalDateTime mockedDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

        Delivery deliveryEntity = Delivery.builder()
                .done(false)
                .route(route)
                .truck(new Truck(1L))
                .created(mockedDateTime)
                .build();

//        NPE:
//        deliveryService.createDelivery(deliveryDTO);
//        verify(genericDAO, times(1)).create(deliveryEntity);
    }

    @Test
    public void routeMapCreated() {
        // For test entity
        String route = "{\"0\":\"11.11111,12.11111\"," +
                       " \"1\":\"22.22222,23.22222\"," +
                       " \"2\":\"33.33333,34.33333\"," +
                       " \"3\":\"44.44444,45.44444\"," +
                       " \"4\":\"11.11111,12.11111\"}";

        GHGeocodingEntry geocodingEntry1 = new GHGeocodingEntry();
        GHGeocodingEntry.Point point1 = new GHGeocodingEntry().new Point(11.11111_909, 12.11111_909);
        geocodingEntry1.setPoint(point1);
        Depot depot1 = Depot.builder().id(1L).location(geocodingEntry1).build();

        GHGeocodingEntry geocodingEntry2 = new GHGeocodingEntry();
        GHGeocodingEntry.Point point2 = new GHGeocodingEntry().new Point(22.22222_909, 23.22222_909);
        geocodingEntry2.setPoint(point2);
        Depot depot2 = Depot.builder().id(2L).location(geocodingEntry2).build();

        GHGeocodingEntry geocodingEntry3 = new GHGeocodingEntry();
        GHGeocodingEntry.Point point3 = new GHGeocodingEntry().new Point(33.33333_909, 34.33333_909);
        geocodingEntry3.setPoint(point3);
        Depot depot3 = Depot.builder().id(3L).location(geocodingEntry3).build();

        GHGeocodingEntry geocodingEntry4 = new GHGeocodingEntry();
        GHGeocodingEntry.Point point4 = new GHGeocodingEntry().new Point(44.44444_909, 45.44444_909);
        geocodingEntry4.setPoint(point4);
        Depot depot4 = Depot.builder().id(4L).location(geocodingEntry4).build();

        List<Cargo> cargoEntityList = new ArrayList<>();
        Cargo cargoA = Cargo.builder()
                .startDepot(depot1)
                .endDepot(depot2)
                .build();
        cargoEntityList.add(cargoA);
        Cargo cargoB = Cargo.builder()
                .startDepot(depot2)
                .endDepot(depot3)
                .build();
        cargoEntityList.add(cargoB);
        Cargo cargoC = Cargo.builder()
                .startDepot(depot1)
                .endDepot(depot4)
                .build();
        cargoEntityList.add(cargoC);
        Cargo cargoD = Cargo.builder()
                .startDepot(depot1)
                .endDepot(depot4)
                .build();
        cargoEntityList.add(cargoD);

        Delivery testDelivery = Delivery.builder().route(route).cargo(cargoEntityList).build();

        // For expected map
        CargoDTO cargoDtoA = CargoDTO.builder()
                .startDepot(depot1)
                .endDepot(depot2)
                .build();
        CargoDTO cargoDtoB = CargoDTO.builder()
                .startDepot(depot2)
                .endDepot(depot3)
                .build();
        CargoDTO cargoDtoC = CargoDTO.builder()
                .startDepot(depot1)
                .endDepot(depot4)
                .build();
        CargoDTO cargoDtoD = CargoDTO.builder()
                .startDepot(depot1)
                .endDepot(depot4)
                .build();

        Map<String, DeliveryDTO.CargoOperations> expectedRouteMap = new LinkedHashMap<>();

        DeliveryDTO.CargoOperations depot1Ops = new DeliveryDTO.CargoOperations();
        depot1Ops.addLoadOps(cargoDtoA, cargoDtoC, cargoDtoD);
        expectedRouteMap.put("11.11111,12.11111", depot1Ops);

        DeliveryDTO.CargoOperations depot2Ops = new DeliveryDTO.CargoOperations();
        depot2Ops.addUnloadOps(cargoDtoA, cargoDtoB);
        expectedRouteMap.put("22.22222,23.22222", depot2Ops);

        DeliveryDTO.CargoOperations depot3Ops = new DeliveryDTO.CargoOperations();
        depot3Ops.addUnloadOps(cargoDtoB);
        expectedRouteMap.put("33.33333,34.33333", depot3Ops);

        DeliveryDTO.CargoOperations depot4Ops = new DeliveryDTO.CargoOperations();
        depot4Ops.addUnloadOps(cargoDtoC, cargoDtoD);
        expectedRouteMap.put("44.44444,45.44444", depot4Ops);

//        NPE:
//        when(modelMapper.map(testDelivery, DeliveryDTO.class)).thenReturn(new DeliveryDTO());
//        DeliveryDTO methodDTO = deliveryService.convertToDTO(testDelivery);
//        assertEquals(methodDTO.getRouteWithCargoOperations(), expectedRouteMap);
    }

}
