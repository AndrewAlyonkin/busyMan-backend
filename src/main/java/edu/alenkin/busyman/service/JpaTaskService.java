package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JpaTaskService implements TaskService{
    @Override
    public Task get(Integer id, Integer userId) {
        return null;
    }

    @Override
    public List<Task> getAll(Integer userId) {
        return null;
    }

    @Override
    public void delete(Integer id, Integer userId) {

    }

    @Override
    public Task create(Task task) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }
}
