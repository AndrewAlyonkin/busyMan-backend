package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static edu.alenkin.busyman.util.ValidationUtils.checkNotFoundWithId;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@AllArgsConstructor
@Slf4j
public class JpaCategoryService implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Category get(Integer id, Integer userId) {
        log.debug("Get category with id {} for user id = {} ", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Category> getAll(Integer userId) {
        log.debug("Get all categories for user id = {} ", userId);
        return repository.getAll(userId);
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.debug("Delete category with id {} for user id = {} ", id, userId);
        checkNotFoundWithId(repository.delete(id, userId) != 0, (int) id);
    }

    @Override
    public Category create(Category category, Integer userId) {
        log.debug("Create category {} ", category);
        Assert.notNull(category, "category can not be empty");
        if (!category.isNew()) {
            return null;
        }
        return repository.save(category, userId);
    }

    @Override
    public Category update(Category category, Integer userId) {
        log.debug("Update priority {} ", category);
        return repository.save(category, userId);
    }
}
