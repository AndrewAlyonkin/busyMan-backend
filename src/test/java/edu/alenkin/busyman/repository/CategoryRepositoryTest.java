package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.testData.CategoryTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.alenkin.busyman.testData.CategoryTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class CategoryRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private JpaCategoryRepository repository;

    @Test
    public void get() {
        assertThat(family)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.get(familyId, 1));
    }

    @Test
    public void getAll() {
        assertThat(List.of(family, work))
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.getAll(1));

        assertThat(List.of(chill, travel))
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.getAll(2));
    }

    @Test
    public void delete() {
        repository.delete(familyId, 1);
        Assertions.assertNull(repository.get(familyId, 1));
    }

    @Test
    public void update() {
        Category updated = CategoryTestData.getUpdatedCategory();
        assertThat(getUpdatedCategory())
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.save(updated, 1));
    }

    @Test
    public void save() {
        Category saved = repository.save(getNewCategory(),1);
        Category expected = getNewCategory();
        expected.setId(saved.getId());
        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(saved);

        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.get(saved.getId(), 1));
    }
}
