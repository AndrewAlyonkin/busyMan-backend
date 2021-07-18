package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.User;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserService {
    User get(Integer id);

    User getByEmail(String email);

    List<User> getAll();

    void delete(Integer id);

    User create(User user);

    User update(User user);

    Integer getCompleted(Integer id);

    Integer getUncompleted(Integer id);
}
