package com.tsystems.transportinfo.data.entity.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.westnordost.osmapi.map.data.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

@Component
public class NodeListConverter implements AttributeConverter<List<Node>, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<Node> nodeList) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(nodeList);
        } catch (final JsonProcessingException e) {
            // TODO: logging
            System.out.println("JSON writing error");
        }

        return customerInfoJson;
    }

    @Override
    public List<Node> convertToEntityAttribute(String nodeJSON) {

        List<Node> nodeList = null;
        try {
            nodeList = objectMapper.readValue(nodeJSON, List.class);
        } catch (final IOException e) {
            // TODO: logging
            System.out.println("JSON reading error");
        }

        return nodeList;
    }

}
