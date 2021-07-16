package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface TaskService {
    Task get(Integer id, Integer userId);

    List<Task> getAll(Integer userId);

    void delete(Integer id, Integer userId);

    Task create(Task task);

    Task update(Task task);
}
