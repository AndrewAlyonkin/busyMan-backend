package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Priority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@AllArgsConstructor
public class JpaPriorityRepository {
    private final PriorityRepository jpaRepository;
    private final UserRepository userRepository;

    public int delete(int id, int userId) {
        return jpaRepository.delete(id, userId);
    }


    public List<Priority> getAll(int userId) {
        return jpaRepository.getAll(userId);
    }

    public Priority get(int id, int userId) {
        return jpaRepository.get(id, userId);
    }

    public Priority save(Priority priority, int userId) {
        priority.setUser(userRepository.getById(userId));
        return jpaRepository.save(priority);
    }
}
