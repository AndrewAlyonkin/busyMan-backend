package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.security.SecurityUtils;
import edu.alenkin.busyman.service.PriorityService;
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
@RequestMapping(PriorityRestController.URL)
@RequiredArgsConstructor
@Slf4j
public class PriorityRestController {
    static final String URL = "/api/v1/priorities";
    private final PriorityService service;

    @GetMapping
    public ResponseEntity<List<Priority>> getAll() {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get all priorities for user {}", userId);
        return ResponseEntity.ok(service.getAll(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> get(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get priority {} for user {}", id, userId);
        return ResponseEntity.ok(service.get(id, userId));
    }

    @PostMapping
    public ResponseEntity<Priority> create(@RequestBody Priority priority) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Creating priority {} for user {}", priority, userId);
        return buildResponse(priority, service.create(priority, userId), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Updating priority {} for user {}", priority, userId);
        return buildResponse(priority, service.create(priority, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Deleting priority {} for user {}", id, userId);
        service.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
