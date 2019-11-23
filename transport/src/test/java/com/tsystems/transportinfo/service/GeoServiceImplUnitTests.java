package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;


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

}
