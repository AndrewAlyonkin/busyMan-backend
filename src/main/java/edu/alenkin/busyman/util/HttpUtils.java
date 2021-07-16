package edu.alenkin.busyman.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class HttpUtils {
    private HttpUtils(){}
    public static <T> ResponseEntity<T> buildResponse(Object methodParameter, T entity, HttpStatus status) {
        if (methodParameter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(entity, status);
    }

    public static <T> ResponseEntity<T> buildResponse(T entity, boolean condition, HttpStatus status) {
        return (entity!=null && condition)
                ? new ResponseEntity<>(entity, status)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
