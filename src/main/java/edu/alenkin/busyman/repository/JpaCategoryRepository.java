package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.repository.crudrepository.CrudCategoryRepository;
import edu.alenkin.busyman.repository.crudrepository.CrudUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@AllArgsConstructor
@Repository
public class JpaCategoryRepository implements CategoryRepository {
    private final CrudCategoryRepository jpaRepository;
    private final CrudUserRepository userRepository;

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
