package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.security.SecurityUtils;
import edu.alenkin.busyman.service.CategoryService;
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
@RequestMapping(CategoryRestController.URL)
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {
    static final String URL = "/api/v1/categories";
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        Integer userId = SecurityUtils.getAuthUserId();
        return ResponseEntity.ok(service.getAll(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        return ResponseEntity.ok(service.get(id, userId));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Integer userId = SecurityUtils.getAuthUserId();
        return buildResponse(category, service.create(category, userId));
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        Integer userId = SecurityUtils.getAuthUserId();
        return buildResponse(category, service.create(category, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer userId = SecurityUtils.getAuthUserId();
        service.delete(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
