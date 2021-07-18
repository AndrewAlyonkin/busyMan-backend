package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.repository.crudrepository.CrudPriorityRepository;
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
public class JpaPriorityRepository implements PriorityRepository {
    private final CrudPriorityRepository jpaRepository;
    private final CrudUserRepository userRepository;

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

    @Override
    public Page<Priority> findByParameter(String search, int userId, Pageable page) {
        return jpaRepository.findByParameter(search, userId, page);
    }
}
