package com.tsystems.transportinfo.data.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.api.model.GHGeocodingEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Slf4j
@Component
public class GeocodingEntryConverter implements AttributeConverter<GHGeocodingEntry, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(GHGeocodingEntry node) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(node);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error");
        }

        return customerInfoJson;
    }

    @Override
    public GHGeocodingEntry convertToEntityAttribute(String nodeJSON) {

        GHGeocodingEntry node = null;
        try {
            node = objectMapper.readValue(nodeJSON, GHGeocodingEntry.class);
        } catch (final IOException e) {
            log.error("JSON reading error");
        }

        return node;
    }

}
