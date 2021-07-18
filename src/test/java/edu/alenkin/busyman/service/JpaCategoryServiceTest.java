package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.repository.CategoryRepository;
import edu.alenkin.busyman.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static edu.alenkin.busyman.testData.CategoryTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class JpaCategoryServiceTest extends AbstractServiceTest {
    private ArgumentCaptor<Integer> idCaptor;
    private ArgumentCaptor<Integer> userIdCaptor;
    private ArgumentCaptor<Category> categoryCaptor;

    @MockBean
    private CategoryRepository repository;

    @Autowired
    @InjectMocks
    private JpaCategoryService service;

    @BeforeEach
    public void dropCaptorsAndMocks() {
        Mockito.reset(repository);
        idCaptor = ArgumentCaptor.forClass(Integer.class);
        userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        categoryCaptor = ArgumentCaptor.forClass(Category.class);
    }

    @Test
    public void get() {
        Mockito.when(repository.get(familyId, 1)).thenReturn(family);
        Category received = service.get(familyId, 1);
        assertThat(family).isEqualTo(received);
        Mockito.verify(repository).get(idCaptor.capture(), userIdCaptor.capture());
        assertThat(familyId).isEqualTo(idCaptor.getValue());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void getNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.get(familyId, 10));
    }

    @Test
    public void getAll() {
        List<Category> all = List.of(family, work, chill);
        Mockito.when(repository.getAll(1)).thenReturn(all);
        List<Category> received = service.getAll(1);
        assertThat(all).isEqualTo(received);
        Mockito.verify(repository).getAll(userIdCaptor.capture());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void delete() {
        Mockito.when(repository.delete(familyId, 1)).thenReturn(1);
        service.delete(familyId, 1);
        Mockito.verify(repository).delete(idCaptor.capture(), userIdCaptor.capture());
        assertThat(familyId).isEqualTo(idCaptor.getValue());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void deleteNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(familyId, 10));
    }

    @Test
    public void create() {
        Category newCategory = getNewCategory();
        Mockito.when(repository.save(newCategory, 1)).thenReturn(family);
        Category received = service.create(newCategory, 1);
        assertThat(received).isEqualTo(family);
        Mockito.verify(repository).save(categoryCaptor.capture(), userIdCaptor.capture());
        assertThat(categoryCaptor.getValue()).isEqualTo(newCategory);
        assertThat(userIdCaptor.getValue()).isEqualTo(1);
    }

    @Test
    public void update() {
        Category updated = getUpdatedCategory();
        Mockito.when(repository.save(updated, 1)).thenReturn(updated);
        Category received = service.update(updated, 1);
        assertThat(received).isEqualTo(updated);
        Mockito.verify(repository).save(categoryCaptor.capture(), userIdCaptor.capture());
        assertThat(categoryCaptor.getValue()).isEqualTo(updated);
        assertThat(userIdCaptor.getValue()).isEqualTo(1);
    }

    @Test
    public void updateNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.update(getUpdatedCategory(), 10));
    }
}
