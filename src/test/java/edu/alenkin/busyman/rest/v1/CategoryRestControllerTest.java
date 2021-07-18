package edu.alenkin.busyman.rest.v1;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.repository.CategoryRepository;
import edu.alenkin.busyman.rest.v1.search.CategorySearch;
import edu.alenkin.busyman.testData.CategoryTestData;
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

import static edu.alenkin.busyman.testData.CategoryTestData.*;
import static edu.alenkin.busyman.util.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class CategoryRestControllerTest extends AbstractControllerTest {

    static final String URL = "/api/v1/categories";

    @MockBean
    private CategoryRepository repository;


    @Test
    public void getAll() throws Exception {
        List<Category> all = List.of(family, work);
        Mockito.when(repository.getAll(1)).thenReturn(all);
        MvcResult res = perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(all, CategoryTestData::assertNoIdEquals)).andReturn();
    }

    @Test
    public void find() throws Exception {
        List<Category> all = List.of(family, work);
        Page<Category> page = new PageImpl<>(all);
        Mockito.when(repository.findByParameter(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(page);

        MvcResult result = perform(MockMvcRequestBuilders.post(URL + "/find")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(new CategorySearch(0, 10, null, null, ""))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Category> actual = JsonUtil.readValues(result.getResponse().getContentAsString(), Category.class);

        assertNoIdEquals(actual, all);
    }

    @Test
    public void get() throws Exception {
        Mockito.when(repository.get(familyId, 1)).thenReturn(family);
        perform(MockMvcRequestBuilders.get(URL + "/" + familyId).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(family, CategoryTestData::assertNoIdEquals));
    }

    @Test
    public void create() throws Exception {
        Category newCategory = getNewCategory();
        Category expected = getNewCategory();
        expected.setId(10);
        Mockito.when(repository.save(Mockito.any(), Mockito.anyInt())).thenReturn(expected);
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(newCategory)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(expected, CategoryTestData::assertNoIdEquals));
    }

    @Test
    public void update() throws Exception {
        Category newCategory = getUpdatedCategory();
        Category expected = getUpdatedCategory();
        Mockito.when(repository.save(Mockito.any(), Mockito.anyInt())).thenReturn(expected);
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(newCategory)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(expected, CategoryTestData::assertNoIdEquals));
    }

    @Test
    public void delete() throws Exception {
        Mockito.when(repository.delete(familyId, 1)).thenReturn(1);
        perform(MockMvcRequestBuilders.delete(URL + "/" + familyId).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
