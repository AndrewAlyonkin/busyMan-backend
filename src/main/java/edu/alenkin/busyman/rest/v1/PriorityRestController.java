package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.rest.v1.search.PrioritySearch;
import edu.alenkin.busyman.security.SecurityUtils;
import edu.alenkin.busyman.service.PriorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.alenkin.busyman.util.HttpUtils.*;

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

    @PostMapping("/find")
    public ResponseEntity<List<Priority>> find(@RequestBody PrioritySearch search) {
        int userId = SecurityUtils.getAuthUserId();
        log.debug("Search priorities with title={} for user {}",search, userId);

        Sort sort = getSort(search);
        PageRequest pageRequest = PageRequest.of(search.getPageNumber(), search.getPageSize(), sort);

        return ResponseEntity.ok(service.findByParameter(search.getTitle(), userId, pageRequest).toList());
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
        return buildResponse(priority, service.update(priority, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Deleting priority {} for user {}", id, userId);
        service.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
