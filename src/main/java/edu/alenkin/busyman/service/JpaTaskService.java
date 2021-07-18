package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static edu.alenkin.busyman.util.ValidationUtils.checkNotFoundWithId;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@AllArgsConstructor
@Slf4j
public class JpaTaskService implements TaskService {
    private final TaskRepository repository;

    @Override
    public Task get(Integer id, Integer userId) {
        log.debug("Get task with id {} for user id = {} ", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Task> getAll(Integer userId) {
        log.debug("Get all tasks for user id = {} ", userId);
        return repository.getAll(userId);
    }

    @Override
    public List<Task> getWithPriority(Integer priorityId, Integer userId) {
        log.debug("Get all tasks with priority {} for user id = {} ",priorityId, userId);
        return repository.getWithPriority(priorityId, userId);
    }

    @Override
    public List<Task> getWithCategory(Integer categoryId, Integer userId) {
        log.debug("Get all tasks with category {} for user id = {} ",categoryId, userId);
        return repository.getWithCategory(categoryId, userId);
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.debug("Delete task with id {} for user id = {} ", id, userId);
        checkNotFoundWithId(repository.delete(id, userId) != 0, (int) id);
    }

    @Override
    public Task create(Task task, Integer userId) {
        log.debug("Create task {} ", task);
        Assert.notNull(task, "category can not be empty");
        if (!task.isNew()) {
            return null;
        }
        return repository.save(task, userId);
    }

    @Override
    public Task update(Task task, Integer userId) {
        log.debug("Update task {} ", task);
        return checkNotFoundWithId(repository.save(task, userId), userId);
    }

    @Override
    public Page<Task> findByParameter(String title, Integer completed, Integer priorityId, Integer categoryId, int userId, Pageable page) {
        log.debug("Search task with parameters title={} completed={} priority={} category={} for user= {} ",
                title, completed, priorityId, categoryId, userId);
        return repository.findByParameter(title, completed, priorityId, categoryId, userId, page);
    }
}
