package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static edu.alenkin.busyman.testData.TaskTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class TaskRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @Test
    public void get() {
        assertThat(taskOne11)
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.get(taskOne11id, 1));
    }

    @Test
    public void getAll() {
        assertThat(List.of(taskOne14, taskOne13, taskOne12, taskOne11))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.getAll(1));

        assertThat(List.of(taskTwo18, taskTwo17, taskTwo16, taskTwo15))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.getAll(2));
    }

    @Test
    public void getWithPriority() {
        assertThat(List.of(taskOne13, taskOne11))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.getWithPriority(7, 1));
    }

    @Test
    public void getWithCategory() {
        assertThat(List.of(taskOne14, taskOne13))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.getWithCategory(4, 1));
    }

    @Test
    public void findForEmptyReq() {
        assertThat(List.of(taskOne14, taskOne13, taskOne12, taskOne11))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .ignoringCollectionOrder()
                .isEqualTo(repository.findByParameter("", null, null, null, 1, PageRequest.of(0, 10)).toList());
    }

    @Test
    public void findByTitle() {
        assertThat(List.of(taskOne11))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .ignoringCollectionOrder()
                .isEqualTo(repository.findByParameter("Купить", null, null, null, 1, PageRequest.of(0, 10)).toList());
    }

    @Test
    public void findByCompleted() {
        assertThat(List.of(taskOne11, taskOne12))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .ignoringCollectionOrder()
                .isEqualTo(repository.findByParameter("", 1, null, null, 1, PageRequest.of(0, 10)).toList());
    }

    @Test
    public void findByPriority() {
        assertThat(List.of(taskOne11, taskOne13))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .ignoringCollectionOrder()
                .isEqualTo(repository.findByParameter("", null, 7, null, 1, PageRequest.of(0, 10)).toList());
    }

    @Test
    public void findByCategory() {
        assertThat(List.of(taskOne13, taskOne14))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .ignoringCollectionOrder()
                .isEqualTo(repository.findByParameter("", null, null, 4, 1, PageRequest.of(0, 10)).toList());
    }

    @Test
    public void delete() {
        repository.delete(taskOne11id, 1);
        Assertions.assertNull(repository.get(taskOne11id, 1));
    }

    @Test
    public void update() {
        Task updated = getUpdatedTask();
        assertThat(getUpdatedTask())
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.save(updated, 1));
    }

    @Test
    public void save() {
        Task saved = repository.save(getNewTask(), 1);
        Task expected = getNewTask();
        expected.setId(saved.getId());
        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(saved);

        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(repository.get(saved.getId(), 1));
    }

}
