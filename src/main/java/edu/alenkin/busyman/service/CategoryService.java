package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.model.Priority;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface CategoryService {
    Category get(Integer id, Integer userId);
    List<Category> getAll(Integer userId);
    void delete(Integer id, Integer userId);
    Category create(Category priority, Integer userId);
    Category update(Category priority, Integer userId);
}
