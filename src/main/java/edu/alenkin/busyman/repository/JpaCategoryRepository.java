package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@AllArgsConstructor
@Repository
public class JpaCategoryRepository {
    private final CategoryRepository jpaRepository;
    private final UserRepository userRepository;

    public int delete(int id, int userId) {
        return jpaRepository.delete(id, userId);
    }

    public List<Category> getAll(int userId){
        return jpaRepository.getAll(userId);
    }

    public Category get(int id, int userId) {
        return jpaRepository.get(id, userId);
    }

    public Category save(Category category, int userId) {
        category.setUser(userRepository.getById(userId));
        return jpaRepository.save(category);
    }
}
