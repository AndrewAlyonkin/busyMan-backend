package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.repository.PriorityRepository;
import edu.alenkin.busyman.rest.v1.search.PrioritySearch;
import edu.alenkin.busyman.testData.PriorityTestData;
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

import static edu.alenkin.busyman.testData.PriorityTestData.*;
import static edu.alenkin.busyman.util.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class PriorityRestControllerTest extends AbstractControllerTest {
    static final String URL = "/api/v1/priorities";

    @MockBean
    private PriorityRepository repository;


    @Test
    public void getAll() throws Exception {
        List<Priority> all = List.of(low, middle);
        Mockito.when(repository.getAll(1)).thenReturn(all);
        MvcResult res = perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(all, PriorityTestData::assertNoIdEquals)).andReturn();
    }

    @Test
    public void find() throws Exception {
        List<Priority> all = List.of(low, middle);
        Page<Priority> page = new PageImpl<>(all);
        Mockito.when(repository.findByParameter(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(page);

        MvcResult result = perform(MockMvcRequestBuilders.post(URL + "/find")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(new PrioritySearch(0, 10, null, null, ""))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Priority> actual = JsonUtil.readValues(result.getResponse().getContentAsString(), Priority.class);

        assertNoIdEquals(actual, all);
    }

    @Test
    public void get() throws Exception {
        Mockito.when(repository.get(lowId, 1)).thenReturn(low);
        perform(MockMvcRequestBuilders.get(URL + "/" + lowId).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(low, PriorityTestData::assertNoIdEquals));
    }

    @Test
    public void create() throws Exception {
        Priority newPriority = getNew();
        Priority expected = getNew();
        expected.setId(10);
        Mockito.when(repository.save(Mockito.any(), Mockito.anyInt())).thenReturn(expected);
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(newPriority)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(expected, PriorityTestData::assertNoIdEquals));
    }

    @Test
    public void update() throws Exception {
        Priority newPriority = getUpdated();
        Priority expected = getUpdated();
        Mockito.when(repository.save(Mockito.any(), Mockito.anyInt())).thenReturn(expected);
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(newPriority)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(expected, PriorityTestData::assertNoIdEquals));
    }

    @Test
    public void delete() throws Exception {
        Mockito.when(repository.delete(lowId, 1)).thenReturn(1);
        perform(MockMvcRequestBuilders.delete(URL + "/" + lowId).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
