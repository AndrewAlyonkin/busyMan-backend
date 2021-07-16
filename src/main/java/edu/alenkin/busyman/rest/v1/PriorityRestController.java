package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.service.PriorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
