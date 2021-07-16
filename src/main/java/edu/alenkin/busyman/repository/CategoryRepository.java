package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Category;

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
}
