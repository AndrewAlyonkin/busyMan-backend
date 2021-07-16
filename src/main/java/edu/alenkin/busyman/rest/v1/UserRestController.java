package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.alenkin.busyman.util.HttpUtils.buildResponse;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(UserRestController.URL)
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
    static final String URL = "/api/v1/users";
    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        log.debug("Get user {}", id);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        log.debug("Get all users");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.debug("Create user {}", user);
        return buildResponse(user, service.create(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        log.debug("Update user {}", user);
        return buildResponse(user, service.update(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        log.debug("Delete user {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
