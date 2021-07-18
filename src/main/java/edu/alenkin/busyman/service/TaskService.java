package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface TaskService {
    Task get(Integer id, Integer userId);

    List<Task> getAll(Integer userId);

    List<Task> getWithPriority(Integer priorityId, Integer userId);

    List<Task> getWithCategory(Integer categoryId, Integer userId);

    void delete(Integer id, Integer userId);

    Task create(Task task, Integer userId);

    Task update(Task task, Integer userId);

    Page<Task> findByParameter(String title, Integer completed, Integer priorityId, Integer categoryId, int userId, Pageable page);
}
