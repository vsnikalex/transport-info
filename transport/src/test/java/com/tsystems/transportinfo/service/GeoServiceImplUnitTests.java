package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.util.shapes.GHPoint;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class GeoServiceImplUnitTests {

    @InjectMocks
    private GeoServiceImpl geoService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void pointExtractedFromEntry() {
        GHGeocodingEntry.Point initPoint = new GHGeocodingEntry().new Point(12.34567, 89.10111);

        GHGeocodingEntry geocodingEntry = new GHGeocodingEntry();
        geocodingEntry.setPoint(initPoint);

        GHPoint methodPoint = geoService.pointFromEntry(geocodingEntry);

        assertEquals(initPoint.getLat(), methodPoint.getLat());
        assertEquals(initPoint.getLng(), methodPoint.getLon());
    }

    @Test
    public void pointExtractedAndStringified() {
        String initCoords = "12.34567,89.10111";
        String[] latLon = initCoords.split(",");

        GHGeocodingEntry.Point point = new GHGeocodingEntry().new Point(12.34567, 89.10111);
        GHGeocodingEntry geocodingEntry = new GHGeocodingEntry();
        geocodingEntry.setPoint(point);

        GeoServiceImpl geoService1 = Mockito.spy(geoService);
        Mockito.doReturn(new GHPoint(12.34567,89.10111)).when(geoService1).pointFromEntry(geocodingEntry);

        String methodCoords = geoService1.pointStringFromEntry(geocodingEntry);
        assertEquals(initCoords, methodCoords);
    }

    @Test
    public void stringCoordsFormatted() {
        String rawCoords1 = "12.34567789,89.10123456";
        String rawCoords2 = "12.345678,89.101231";

        String processedCoords1 = geoService.format(rawCoords1);
        String processedCoords2 = geoService.format(rawCoords2);

        assertEquals(processedCoords1, processedCoords2);
    }

    @Test
    public void stringDifferentCoordsFormatted() {
        String rawCoords1 = "12.34566999,89.10122999";
        String rawCoords2 = "12.345678,89.101231";

        String processedCoords1 = geoService.format(rawCoords1);
        String processedCoords2 = geoService.format(rawCoords2);

        assertThat(processedCoords1, is(not(processedCoords2)));
    }

    @Test
    public void distanceCalculated() {
        // Italy : Rome
        GHPoint firstPoint = new GHPoint(41.902782,12.496366);
        double firstAlt = 13.0;
        // Austria : Salzburg
        GHPoint secondPoint = new GHPoint(47.811195,13.033229);
        double secondAlt = 424.0;

        // Distance according to http://boulter.com/gps/distance
        double straightDistanceKm = 657.96;
        double estimatedDistanceMeters =  geoService.distance(firstPoint.getLat(), secondPoint.getLat(),
                                                        firstPoint.getLon(), secondPoint.getLon(),
                                                        firstAlt,            secondAlt);

        double absDif = Math.abs(straightDistanceKm - estimatedDistanceMeters/1000);

        // Error is less than 1 kilometer
        assertEquals(Double.compare(absDif, 1.0), -1);
    }

    @Test
    public void sameCityCheckedTrue() {
        GHGeocodingEntry firstGeoObject = new GHGeocodingEntry();
        firstGeoObject.setCountry("Germany");
        firstGeoObject.setState("Low Saxon");
        firstGeoObject.setCity("Hamburg");
        firstGeoObject.setName("Truck");

        GHGeocodingEntry secondGeoObject = new GHGeocodingEntry();
        secondGeoObject.setCountry("Germany");
        secondGeoObject.setState("Low Saxon");
        secondGeoObject.setCity("Hamburg");
        secondGeoObject.setName("Driver");

        assertTrue(geoService.inSameCity(firstGeoObject, secondGeoObject));
    }

    @Test
    public void sameCityCheckedFalse() {
        GHGeocodingEntry firstGeoObject = new GHGeocodingEntry();
        firstGeoObject.setCountry("Germany");
        firstGeoObject.setState("Low Saxon");
        firstGeoObject.setCity("Hamburg");
        firstGeoObject.setName("Truck");

        GHGeocodingEntry secondGeoObject = new GHGeocodingEntry();
        secondGeoObject.setCountry("Germany");
        secondGeoObject.setState("Brandenburg");
        secondGeoObject.setCity("Potsdam");
        secondGeoObject.setName("Driver");

        assertFalse(geoService.inSameCity(firstGeoObject, secondGeoObject));
    }

}
