package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
