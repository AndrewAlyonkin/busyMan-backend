package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static edu.alenkin.busyman.testData.CategoryTestData.*;
import static edu.alenkin.busyman.testData.PriorityTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class PriorityRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private PriorityRepository repository;

    @Test
    public void get() {
        assertThat(low)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.get(lowId, 1));
    }

    @Test
    public void getAll() {
        assertThat(List.of(low, middle))
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.getAll(1));

        assertThat(List.of(high, superHigh))
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.getAll(2));
    }

    @Test
    public void findByParameter() {
        assertThat(List.of(low))
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.findByParameter("low", 1, PageRequest.of(0, 10)).toList());

        assertThat(List.of(low, middle))
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.findByParameter("", 1, PageRequest.of(0, 10)).toList());
    }




    @Test
    public void delete() {
        repository.delete(lowId, 1);
        Assertions.assertNull(repository.get(lowId, 1));
    }

    @Test
    public void update() {
        Priority updated = getUpdated();
        assertThat(getUpdated())
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(repository.save(updated,1));
    }

    @Test
    public void save() {
        Priority saved = repository.save(getNew(),1);
        Priority expected = getNew();
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
