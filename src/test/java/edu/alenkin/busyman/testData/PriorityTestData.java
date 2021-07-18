package edu.alenkin.busyman.testData;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.model.Priority;
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
public class PriorityTestData {

    public final static int lowId = 7;
    public final static int midId = 8;
    public final static int higId = 9;
    public final static int supHigId = 10;
    public final static Priority low = new Priority(lowId, new User(1), "Низкий", "#caffdd");
    public final static Priority middle = new Priority(midId, new User(1), "Средний", "#883bdc");
    public final static Priority high = new Priority(higId, new User(2), "Высокий", "#f05f5f");
    public final static Priority superHigh = new Priority(supHigId, new User(2), "Очень Высокий", "#f05f5f");

    public static Priority getNew() {
        return new Priority(null, new User(1), "Низкий", "#caffdd");
    }

    public static Priority getUpdated() {
        Priority updated = new Priority(middle);
        updated.setColor("new");
        updated.setTitle("New");
        return updated;
    }

    public static ResultMatcher jsonMatcher(Priority expected, BiConsumer<Priority, Priority> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asCategory(mvcResult), expected);
    }

    public static ResultMatcher jsonMatcher(List<Priority> expected, BiConsumer<List<Priority>, List<Priority>> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asCategories(mvcResult), expected);
    }

    public static Priority asCategory(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, Priority.class);
    }

    public static List<Priority> asCategories(MvcResult mvcResult) throws IOException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValues(jsonActual, Priority.class);
    }

    public static void assertNoIdEquals(Priority actual, Priority expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(expected);
    }
    public static void assertNoIdEquals(List<Priority> actual, List<Priority> expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("user")
                .isEqualTo(expected);
    }
}
