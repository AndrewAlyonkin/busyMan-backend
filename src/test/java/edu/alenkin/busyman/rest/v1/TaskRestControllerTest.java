package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.repository.TaskRepository;
import edu.alenkin.busyman.rest.v1.search.TaskSearch;
import edu.alenkin.busyman.testData.TaskTestData;
import edu.alenkin.busyman.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static edu.alenkin.busyman.testData.TaskTestData.*;
import static edu.alenkin.busyman.util.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class TaskRestControllerTest extends AbstractControllerTest {

    static final String URL = "/api/v1/tasks";

    @MockBean
    private TaskRepository repository;


    @Test
    public void getAll() throws Exception {
        List<Task> all = List.of(taskOne11, taskOne12);
        Mockito.when(repository.getAll(1)).thenReturn(all);
        MvcResult res = perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(all, TaskTestData::assertNoIdEquals)).andReturn();
    }

    @Test
    public void find() throws Exception {
        List<Task> all = List.of(taskOne11, taskOne12);
        Page<Task> page = new PageImpl<>(all);
        Mockito.when(repository.findByParameter(Mockito.any(), Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(page);

        MvcResult result = perform(MockMvcRequestBuilders.post(URL + "/find")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(new TaskSearch(0, 10, null, null,null,null, null,null))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Task> actual = JsonUtil.readValues(result.getResponse().getContentAsString(), Task.class);

        assertNoIdEquals(actual, all);
    }

    @Test
    public void get() throws Exception {
        Mockito.when(repository.get(taskOne11id, 1)).thenReturn(taskOne11);
        perform(MockMvcRequestBuilders.get(URL + "/" + taskOne11id).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(taskOne11, TaskTestData::assertNoIdEquals));
    }

    @Test
    public void create() throws Exception {
        Task newTask = getNewTask();
        Task expected = getNewTask();
        expected.setId(10);
        Mockito.when(repository.save(Mockito.any(), Mockito.anyInt())).thenReturn(expected);
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(newTask)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(expected, TaskTestData::assertNoIdEquals));
    }

    @Test
    public void update() throws Exception {
        Task newTask = getUpdatedTask();
        Task expected = getUpdatedTask();
        Mockito.when(repository.save(Mockito.any(), Mockito.anyInt())).thenReturn(expected);
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(newTask)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(expected, TaskTestData::assertNoIdEquals));
    }

    @Test
    public void delete() throws Exception {
        Mockito.when(repository.delete(taskOne11id, 1)).thenReturn(1);
        perform(MockMvcRequestBuilders.delete(URL + "/" + taskOne11id).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
