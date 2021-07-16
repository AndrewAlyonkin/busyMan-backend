package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.security.SecurityUtils;
import edu.alenkin.busyman.service.TaskService;
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
@RequestMapping(TaskRestController.URL)
@RequiredArgsConstructor
@Slf4j
public class TaskRestController {
    static final String URL = "/api/v1/tasks";
    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get all tasks for user {}", userId);
        return ResponseEntity.ok(service.getAll(userId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getWithCategories(@PathVariable Integer categoryId) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get all tasks for user {} with category {}", userId, categoryId);
        return ResponseEntity.ok(service.getWithCategory(categoryId, userId));
    }

    @GetMapping("/priority/{priorityId}")
    public ResponseEntity<List<Task>> getWithPriorities(@PathVariable Integer priorityId) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get all tasks for user {} with priority {}", userId, priorityId);
        return ResponseEntity.ok(service.getWithPriority(priorityId, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get task {} for user {}", id, userId);
        return ResponseEntity.ok(service.get(id, userId));
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Creating task {} for user {}", task, userId);
        return buildResponse(task, service.create(task, userId));
    }

    @PutMapping
    public ResponseEntity<Task> update(@RequestBody Task task) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Updating task {} for user {}", task, userId);
        return buildResponse(task, service.create(task, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Deleting task {} for user {}", id, userId);
        service.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
