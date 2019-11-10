package com.tsystems.transportinfo.data.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.util.shapes.GHPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class PointListConverter implements AttributeConverter<List<GHPoint>, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<GHPoint> nodeList) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(nodeList);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error");
        }

        return customerInfoJson;
    }

    @Override
    public List<GHPoint> convertToEntityAttribute(String nodeJSON) {

        List<GHPoint> nodeList = null;
        try {
            nodeList = objectMapper.readValue(nodeJSON, List.class);
        } catch (final IOException e) {
            log.error("JSON reading error");
        }

        return nodeList;
    }

}
