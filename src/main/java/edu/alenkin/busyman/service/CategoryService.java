package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface CategoryService {
    Category get(Integer id, Integer userId);

    List<Category> getAll(Integer userId);

    void delete(Integer id, Integer userId);

    Category create(Category category, Integer userId);

    Category update(Category category, Integer userId);

    Page<Category> findByParameter(String search, int userId, Pageable page);
}
