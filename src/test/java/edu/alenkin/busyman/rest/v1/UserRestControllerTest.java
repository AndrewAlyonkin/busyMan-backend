package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

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

}
