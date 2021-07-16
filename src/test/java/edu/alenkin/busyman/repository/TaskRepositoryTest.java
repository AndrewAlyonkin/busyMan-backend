package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.testData.TaskTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.alenkin.busyman.testData.TaskTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
                .isEqualTo(repository.getAll( 2));
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
                .isEqualTo(repository.save(updated));
    }

    @Test
    public void save() {
        Task saved = repository.save(getNewTask());
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
