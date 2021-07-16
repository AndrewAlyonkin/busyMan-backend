package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.repository.crudrepository.CrudUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static edu.alenkin.busyman.util.ValidationUtils.checkNotFound;
import static edu.alenkin.busyman.util.ValidationUtils.checkNotFoundWithId;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Slf4j
@AllArgsConstructor
@Service
public class JpaUserService implements UserService {

    private final CrudUserRepository repository;

    @Override
    public User get(Integer id) {
        return checkNotFoundWithId(repository.findById(id).get(), id);
    }

    @Override
    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email), email);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        return checkNotFoundWithId(repository.save(user), user.getId());
    }
}
