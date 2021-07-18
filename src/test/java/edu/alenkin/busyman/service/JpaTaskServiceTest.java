package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.repository.TaskRepository;
import edu.alenkin.busyman.testData.TaskTestData;
import edu.alenkin.busyman.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static edu.alenkin.busyman.testData.TaskTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class JpaTaskServiceTest extends AbstractServiceTest {
    private ArgumentCaptor<Integer> idCaptor;
    private ArgumentCaptor<Integer> userIdCaptor;
    private ArgumentCaptor<Task> taskCaptor;

    @MockBean
    private TaskRepository repository;

    @Autowired
    @InjectMocks
    private JpaTaskService service;

    @BeforeEach
    public void dropCaptorsAndMocks() {
        Mockito.reset(repository);
        idCaptor = ArgumentCaptor.forClass(Integer.class);
        userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        taskCaptor = ArgumentCaptor.forClass(Task.class);
    }

    @Test
    public void get() {
        Mockito.when(repository.get(TaskTestData.taskOne11id, 1)).thenReturn(taskOne11);
        Task received = service.get(taskOne11id, 1);
        assertThat(taskOne11).isEqualTo(received);
        Mockito.verify(repository).get(idCaptor.capture(), userIdCaptor.capture());
        assertThat(taskOne11id).isEqualTo(idCaptor.getValue());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void getNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.get(taskOne11id, 10));
    }

    @Test
    public void getAll() {
        List<Task> all = List.of(taskOne11, taskOne12, taskOne13);
        Mockito.when(repository.getAll(1)).thenReturn(all);
        List<Task> received = service.getAll(1);
        assertThat(all).isEqualTo(received);
        Mockito.verify(repository).getAll(userIdCaptor.capture());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void find() {
        List<Task> all = List.of(taskOne11, taskOne12, taskOne13);
        Page<Task> page = new PageImpl<>(all);
        Mockito.when(repository.findByParameter(Mockito.any(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenReturn(page);
        Page<Task> received = service.findByParameter("", 1, 4, 2, 1, PageRequest.of(0, 10));
        assertThat(page).isEqualTo(received);
    }

    @Test
    public void getWithPriority() {
        List<Task> all = List.of(taskOne13, taskOne11);
        Mockito.when(repository.getWithPriority(1, 1)).thenReturn(all);
        List<Task> received = service.getWithPriority(1, 1);
        assertThat(all).isEqualTo(received);
    }

    @Test
    public void getWithCategory() {
        List<Task> all = List.of(taskOne14, taskOne13);
        Mockito.when(repository.getWithCategory(1, 1)).thenReturn(all);
        List<Task> received = service.getWithCategory(1, 1);
        assertThat(all).isEqualTo(received);
    }

    @Test
    public void delete() {
        Mockito.when(repository.delete(taskOne11id, 1)).thenReturn(1);
        service.delete(taskOne11id, 1);
        Mockito.verify(repository).delete(idCaptor.capture(), userIdCaptor.capture());
        assertThat(taskOne11id).isEqualTo(idCaptor.getValue());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void deleteNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(taskOne11id, 10));
    }

    @Test
    public void create() {
        Task newTask = getNewTask();
        Mockito.when(repository.save(newTask, 1)).thenReturn(taskOne11);
        Task received = service.create(newTask, 1);
        assertThat(received).isEqualTo(taskOne11);
        Mockito.verify(repository).save(taskCaptor.capture(), userIdCaptor.capture());
        assertThat(taskCaptor.getValue()).isEqualTo(newTask);
        assertThat(userIdCaptor.getValue()).isEqualTo(1);
    }

    @Test
    public void updateNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.update(getUpdatedTask(), 10));
    }

}
