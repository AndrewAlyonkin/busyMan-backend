package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

import static edu.alenkin.busyman.util.HttpUtils.buildResponse;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class UserRestControllerTest extends AbstractControllerTest {
    static final String URL = "/api/categories";

    private ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
    private ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    UserService service;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testContextEnvironment() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("userRestController"));
    }




//
//    static final String URL = "/api/v1/users";
//    private final UserService service;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> get(@PathVariable Integer id) {
//        return ResponseEntity.ok(service.get(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAll() {
//        return ResponseEntity.ok(service.getAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<User> create(@RequestBody User user) {
//        return buildResponse(user, service.create(user), HttpStatus.CREATED);
//    }
//
//    @PutMapping
//    public ResponseEntity<User> update(@RequestBody User user) {
//        return buildResponse(user, service.update(user), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        service.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/completed/{id}")
//    public ResponseEntity<Integer> getCompleted(@PathVariable Integer id) {
//        return ResponseEntity.ok(service.getCompleted(id));
//    }
//
//    @GetMapping("/uncompleted/{id}")
//    public ResponseEntity<Integer> getUncompleted(@PathVariable Integer id) {
//        return ResponseEntity.ok(service.getUncompleted(id));
//    }

}
