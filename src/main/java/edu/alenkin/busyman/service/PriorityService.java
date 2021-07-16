package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Priority;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface PriorityService {
    Priority get(Integer id, Integer userId);

    List<Priority> getAll(Integer userId);

    void delete(Integer id, Integer userID);

    Priority create(Priority priority);

    Priority update(Priority priority);
}
