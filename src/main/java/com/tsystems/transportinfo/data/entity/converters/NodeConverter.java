package com.tsystems.transportinfo.data.entity.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.westnordost.osmapi.map.data.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Component
public class NodeConverter implements AttributeConverter<Node, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Node node) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(node);
        } catch (final JsonProcessingException e) {
            // TODO: logging
            System.out.println("JSON writing error");
        }

        return customerInfoJson;
    }

    @Override
    public Node convertToEntityAttribute(String nodeJSON) {

        Node node = null;
        try {
            node = objectMapper.readValue(nodeJSON, Node.class);
        } catch (final IOException e) {
            // TODO: logging
            System.out.println("JSON reading error");
        }

        return node;
    }

}
