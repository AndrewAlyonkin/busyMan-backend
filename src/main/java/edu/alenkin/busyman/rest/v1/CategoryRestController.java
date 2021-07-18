package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.rest.v1.search.CategorySearch;
import edu.alenkin.busyman.security.SecurityUtils;
import edu.alenkin.busyman.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static edu.alenkin.busyman.util.HttpUtils.buildResponse;
import static edu.alenkin.busyman.util.HttpUtils.getSort;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(CategoryRestController.URL)
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {
    static final String URL = "/api/v1/categories";
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get all categories for user {}", userId);
        return ResponseEntity.ok(service.getAll(userId));
    }

    @PostMapping("/find")
    public ResponseEntity<List<Category>> find(@RequestBody CategorySearch search) {
        int userId = SecurityUtils.getAuthUserId();
        log.debug("Search categories with title={} for user {}", search, userId);

        Sort sort = getSort(search);
        PageRequest pageRequest = PageRequest.of(Objects.requireNonNullElse(search.getPageNumber(), 0),
                Objects.requireNonNullElse(search.getPageSize(), 20), sort);

        return ResponseEntity.ok(service.findByParameter(search.getTitle(), userId, pageRequest).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Get category {} for user {}", id, userId);
        return ResponseEntity.ok(service.get(id, userId));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Creating category {} for user {}", category, userId);
        return buildResponse(category, service.create(category, userId), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Updating category {} for user {}", category, userId);
        return buildResponse(category, service.update(category, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        log.debug("Deleting category {} for user {}", id, userId);
        service.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
