package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.repository.crudrepository.CrudUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static edu.alenkin.busyman.testData.UserTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class JpaUserServiceTest extends AbstractServiceTest {
    @MockBean
    private CrudUserRepository repository;

    @InjectMocks
    @Autowired
    private JpaUserService service;


    private ArgumentCaptor<Integer> idCaptor;
    private ArgumentCaptor<Integer> userIdCaptor;
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    public void dropCaptorsAndMocks() {
        Mockito.reset(repository);
        idCaptor = ArgumentCaptor.forClass(Integer.class);
        userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        userCaptor = ArgumentCaptor.forClass(User.class);
    }


    @Test
    public void get() {
        Mockito.when(repository.findById(userId)).thenReturn(Optional.of(user));
        User received = service.get(userId);
        assertThat(received).isEqualTo(user);
        Mockito.verify(repository).findById(idCaptor.capture());
        assertThat(userId).isEqualTo(idCaptor.getValue());
    }

    @Test
    public void notFound() {
        Mockito.when(repository.findById(userId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> service.get(userId));
    }

    @Test
    public void getByEmail() {
        Mockito.when(repository.getByEmail("test")).thenReturn(user);
        User received = service.getByEmail("test");
        assertThat(received).isEqualTo(user);
        Mockito.verify(repository).getByEmail("test");
    }

    @Test
    public void getAll() {
        List<User> all = List.of(user, admin);
        Mockito.when(repository.findAll()).thenReturn(all);
        List<User> received = service.getAll();
        assertThat(all).isEqualTo(received);
        Mockito.verify(repository).findAll();
    }

    @Test
    public void delete() {
        Mockito.when(repository.delete(userId)).thenReturn(1);
        service.delete(userId);
        Mockito.verify(repository).delete(idCaptor.capture());
        assertThat(userId).isEqualTo(idCaptor.getValue());
    }

    @Test
    public void create() {
        User newUser = getNew();
        Mockito.when(repository.save(newUser)).thenReturn(user);
        User received = service.create(newUser);
        assertThat(received).isEqualTo(user);
        Mockito.verify(repository).save(userCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(newUser);
    }

    @Test
    public void update() {
        User updated = getUpdatedUser();
        Mockito.when(repository.save(updated)).thenReturn(updated);
        User received = service.update(updated);
        assertThat(received).isEqualTo(updated);
        Mockito.verify(repository).save(userCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(updated);
    }
}
