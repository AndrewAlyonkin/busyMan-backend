package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void delete(Integer id, Integer userId) {
        log.debug("Delete category with id {} for user id = {} ", id, userId);
        checkNotFoundWithId(repository.delete(id, userId) != 0, (int) id);
    }

    @Override
    public Task create(Task task, Integer userId) {
        log.debug("Create category {} ", task);
        Assert.notNull(task, "category can not be empty");
        if (!task.isNew()) {
            return null;
        }
        return repository.save(task, userId);
    }

    @Override
    public Task update(Task task, Integer userId) {
        log.debug("Update priority {} ", task);
        return checkNotFoundWithId(repository.save(task, userId), userId);
    }
}
