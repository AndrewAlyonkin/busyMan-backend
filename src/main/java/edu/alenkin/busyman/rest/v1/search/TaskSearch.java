package edu.alenkin.busyman.rest.v1.search;

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
public class TaskSearch extends AbstractSearch {
    private String title;
    private Integer completed;
    private Integer priorityId;
    private Integer categoryId;

    public TaskSearch(Integer pageNumber, Integer pageSize, String sortColumn,
                      String sortDirection, String title, Integer completed,
                      Integer priorityId, Integer categoryId) {
        super(pageNumber, pageSize, sortColumn, sortDirection);
        this.title = title;
        this.completed = completed;
        this.priorityId = priorityId;
        this.categoryId = categoryId;
    }
}
