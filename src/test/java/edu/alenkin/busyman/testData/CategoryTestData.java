package edu.alenkin.busyman.testData;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.util.JsonUtil;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class CategoryTestData {
    public final static int familyId = 3;
    public final static int workId = 4;
    public final static int chillId = 5;
    public final static int travelId = 6;
    public static Category family = new Category(familyId, new User(1), "Family", 1, 0);
    public static Category work = new Category(workId, new User(1), "Work", 0, 2);
    public static Category chill = new Category(chillId, new User(2), "Chill", 0, 1);
    public static Category travel = new Category(travelId, new User(2), "Travel", 0, 1);

    public static Category getNewCategory() {
        return new Category(null, new User(1), "New", 0, 0);
    }

    public static Category getUpdatedCategory() {
        Category forUpdate = new Category(family);
        forUpdate.setUser(new User(2));
        forUpdate.setTitle("New");
        return forUpdate;
    }

    public static ResultMatcher jsonMatcher(Category expected, BiConsumer<Category, Category> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asCategory(mvcResult), expected);
    }

    public static ResultMatcher jsonMatcher(List<Category> expected, BiConsumer<List<Category>, List<Category>> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asCategories(mvcResult), expected);
    }

    public static Category asCategory(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, Category.class);
    }

    public static List<Category> asCategories(MvcResult mvcResult) throws IOException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValues(jsonActual, Category.class);
    }

    public static void assertNoIdEquals(Category actual, Category expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(expected);
    }
    public static void assertNoIdEquals(List<Category> actual, List<Category> expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(expected);
    }

}
