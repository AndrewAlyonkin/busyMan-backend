package edu.alenkin.busyman.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@UtilityClass
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static <T> String writeValue(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> List<T> readValues(String json, Class<T> clazz) throws IOException {
        ObjectReader reader = objectMapper.readerFor(clazz);
        return reader.<T>readValues(json).readAll();
    }
    public static <T> T readValue(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

}


