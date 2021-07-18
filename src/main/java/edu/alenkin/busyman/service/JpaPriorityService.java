package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.repository.PriorityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static edu.alenkin.busyman.util.ValidationUtils.checkNotFoundWithId;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@AllArgsConstructor
@Slf4j
public class JpaPriorityService implements PriorityService {
    private final PriorityRepository repository;

    @Override
    public Priority get(Integer id, Integer userId) {
        log.debug("Get priority with id {} for user id = {} ", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Priority> getAll(Integer userId) {
        log.debug("Get all priorities for user id = {} ", userId);
        return repository.getAll(userId);
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.debug("Delete priority with id {} for user id = {} ", id, userId);
        checkNotFoundWithId(repository.delete(id, userId) != 0, (int) id);
    }

    @Override
    public Priority create(Priority priority, Integer userId) {
        log.debug("Create category {} ", priority);
        Assert.notNull(priority, "category can not be empty");
        if (!priority.isNew()) {
            return null;
        }
        return repository.save(priority, userId);
    }

    @Override
    public Priority update(Priority priority, Integer userId) {
        log.debug("Update priority {} ", priority);
        return checkNotFoundWithId(repository.save(priority, userId), userId);
    }

    @Override
    public Page<Priority> findByParameter(String search, int userId, Pageable page) {
        log.debug("Get priority with title {} ", search);
        return repository.findByParameter(search, userId, page);
    }
}
