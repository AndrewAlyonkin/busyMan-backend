package edu.alenkin.busyman.rest.v1.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractSearch {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;

    public AbstractSearch(Integer pageNumber, Integer pageSize, String sortColumn, String sortDirection) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
    }
}
