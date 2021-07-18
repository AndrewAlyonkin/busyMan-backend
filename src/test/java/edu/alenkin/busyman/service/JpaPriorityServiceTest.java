package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.repository.PriorityRepository;
import edu.alenkin.busyman.testData.PriorityTestData;
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

import static edu.alenkin.busyman.testData.PriorityTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class JpaPriorityServiceTest extends AbstractServiceTest {
    private ArgumentCaptor<Integer> idCaptor;
    private ArgumentCaptor<Integer> userIdCaptor;
    private ArgumentCaptor<Priority> priorityCaptor;

    @MockBean
    private PriorityRepository repository;

    @Autowired
    @InjectMocks
    private JpaPriorityService service;

    @BeforeEach
    public void dropCaptorsAndMocks() {
        Mockito.reset(repository);
        idCaptor = ArgumentCaptor.forClass(Integer.class);
        userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        priorityCaptor = ArgumentCaptor.forClass(Priority.class);
    }

    @Test
    public void get() {
        Mockito.when(repository.get(PriorityTestData.lowId, 1)).thenReturn(low);
        Priority received = service.get(lowId, 1);
        assertThat(low).isEqualTo(received);
        Mockito.verify(repository).get(idCaptor.capture(), userIdCaptor.capture());
        assertThat(lowId).isEqualTo(idCaptor.getValue());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void getNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.get(lowId, 10));
    }

    @Test
    public void getAll() {
        List<Priority> all = List.of(low, middle, high);
        Mockito.when(repository.getAll(1)).thenReturn(all);
        List<Priority> received = service.getAll(1);
        assertThat(all).isEqualTo(received);
        Mockito.verify(repository).getAll(userIdCaptor.capture());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void findByTitle() {
        List<Priority> all = List.of(low, middle, high);
        Page<Priority> page = new PageImpl<>(all);
        Mockito.when(repository.findByParameter(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(page);
        Page<Priority> received = service.findByParameter("", 1, PageRequest.of(0, 10));
        assertThat(page).isEqualTo(received);
    }

    @Test
    public void delete() {
        Mockito.when(repository.delete(lowId, 1)).thenReturn(1);
        service.delete(lowId, 1);
        Mockito.verify(repository).delete(idCaptor.capture(), userIdCaptor.capture());
        assertThat(lowId).isEqualTo(idCaptor.getValue());
        assertThat(1).isEqualTo(userIdCaptor.getValue());
    }

    @Test
    public void deleteNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(lowId, 10));
    }

    @Test
    public void create() {
        Priority newPriority = getNew();
        Mockito.when(repository.save(newPriority, 1)).thenReturn(low);
        Priority received = service.create(newPriority, 1);
        assertThat(received).isEqualTo(low);
        Mockito.verify(repository).save(priorityCaptor.capture(), userIdCaptor.capture());
        assertThat(priorityCaptor.getValue()).isEqualTo(newPriority);
        assertThat(userIdCaptor.getValue()).isEqualTo(1);
    }

    @Test
    public void updateNotOwn() {
        Assertions.assertThrows(NotFoundException.class, () -> service.update(getUpdated(), 10));
    }

}
