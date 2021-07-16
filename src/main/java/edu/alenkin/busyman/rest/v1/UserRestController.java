package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
