package edu.alenkin.busyman.util;

import edu.alenkin.busyman.rest.v1.search.AbstractSearch;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class HttpUtils {
    private HttpUtils() {
    }

    public static <T> ResponseEntity<T> buildResponse(Object methodParameter, T entity, HttpStatus status) {
        if (methodParameter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(entity, status);
    }

    public static <T> ResponseEntity<T> buildResponse(T entity, boolean condition, HttpStatus status) {
        return (entity != null && condition)
                ? new ResponseEntity<>(entity, status)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public static Sort getSort(AbstractSearch search) {
        String sortColumn = search.getSortColumn() == null ? "title" : search.getSortColumn();
        String sortDirection = search.getSortDirection();

        Sort.Direction direction = (sortDirection != null && sortDirection.contains("desc"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(direction, sortColumn);


    }
}
