package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.repository.crudrepository.CrudUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

import static edu.alenkin.busyman.testData.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CrudUserRepository repository;

    @Test
    public void get() {
        assertThat(admin)
                .usingRecursiveComparison()
                .ignoringFields("registered")
                .isEqualTo(repository.findById(adminId).orElse(null));
    }

    @Test
    public void getAll() {
        assertThat(List.of(user, admin))
                .usingRecursiveComparison()
                .ignoringFields("registered")
                .isEqualTo(repository.findAll());
    }

    @Test
    public void delete() {
        repository.delete(userId);
        Assertions.assertThrows(NoSuchElementException.class, () ->
                repository.findById(userId).get());
    }

    @Test
    public void deleteNotFound() {
        assertThat(0).isEqualTo(repository.delete(1000));
    }

    @Test
    public void getNotFound() {
        Assertions.assertThrows(NoSuchElementException.class, () ->
                repository.findById(1000).get());
    }

    @Test
    public void update() {
        User updated = getUpdatedUser();
        assertThat(getUpdatedUser())
                .usingRecursiveComparison()
                .ignoringFields("registered")
                .isEqualTo(repository.save(updated));
    }

    @Test
    public void save() {
        User saved = repository.save(getNew());
        User expected = getNew();
        expected.setId(saved.getId());
        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("registered")
                .isEqualTo(saved);

        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("registered")
                .isEqualTo(repository.findById(expected.getId()).orElse(null));
    }

}
