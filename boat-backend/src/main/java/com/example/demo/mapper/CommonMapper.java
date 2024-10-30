package com.example.demo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public abstract class CommonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serializeObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertObjectListToJson(List<?> objectList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(objectList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
