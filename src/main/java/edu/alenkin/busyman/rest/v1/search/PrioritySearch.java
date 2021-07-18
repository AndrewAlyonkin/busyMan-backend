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
public class PrioritySearch extends AbstractSearch {
    private String title;

    public PrioritySearch(Integer pageNumber, Integer pageSize, String sortColumn, String sortDirection, String title) {
        super(pageNumber, pageSize, sortColumn, sortDirection);
        this.title = title;
    }
}
