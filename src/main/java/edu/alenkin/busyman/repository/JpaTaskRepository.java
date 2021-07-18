package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.repository.crudrepository.CrudTaskRepository;
import edu.alenkin.busyman.repository.crudrepository.CrudUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@AllArgsConstructor
public class JpaTaskRepository implements TaskRepository {
    private final CrudTaskRepository jpaRepository;
    private final CrudUserRepository userRepository;

    public int delete(int id, int userId) {
        return jpaRepository.delete(id, userId);
    }

    public List<Task> getAll(int userId) {
        return jpaRepository.getAll(userId);
    }

    public Task get(int id, int userId) {
        return jpaRepository.get(id, userId);
    }

    public Task save(Task task, int userId) {
        task.setUser(userRepository.getById(userId));
        return jpaRepository.save(task);
    }

    @Override
    public List<Task> getWithPriority(int priorityId, int userId) {
        return jpaRepository.getWithPriority(priorityId, userId);
    }

    @Override
    public List<Task> getWithCategory(int categoryId, int userId) {
        return jpaRepository.getWithCategory(categoryId, userId);
    }

    @Override
    public Page<Task> findByParameter(String title, Integer completed, Integer priorityId, Integer categoryId, int userId, Pageable page) {
        return jpaRepository.findByParameter(title, completed, priorityId, categoryId, userId, page);
    }
}
