package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface CategoryRepository {
    int delete(int id, int userId);

    List<Category> getAll(int userId);

    Category get(int id, int userId);

    Category save(Category category, int userId);

    Page<Category> findByParameter(String search, int userId, Pageable page);
}
