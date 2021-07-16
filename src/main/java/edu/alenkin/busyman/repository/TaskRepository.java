package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Task;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface TaskRepository {
    int delete(int id, int userId);

    List<Task> getAll(int userId);

    Task get(int id, int userId);

    Task save(Task task, int userId);

    List<Task> getWithPriority(int priorityId, int userId);

    List<Task> getWithCategory(int categoryId, int userId);
}
