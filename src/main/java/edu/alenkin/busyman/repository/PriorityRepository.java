package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Priority;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface PriorityRepository {
    int delete(int id, int userId);

    List<Priority> getAll(int userId);

    Priority get(int id, int userId);

    Priority save(Priority priority, int userId);

}
