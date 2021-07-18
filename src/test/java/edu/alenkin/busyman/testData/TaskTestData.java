package edu.alenkin.busyman.testData;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.model.User;
import edu.alenkin.busyman.util.JsonUtil;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class TaskTestData {
    public final static int taskOne11id = 11;
    public final static int taskOne12id = 12;
    public final static int taskOne13id = 13;
    public final static int taskOne14id = 14;
    public final static int taskTwo15id = 15;
    public final static int taskTwo16id = 16;
    public final static int taskTwo17id = 17;
    public final static int taskTwo18id = 18;

    public final static Task taskOne11 = new Task(11, new User(1), "Купить хлеба", 1, LocalDateTime.parse("2018-04-29T15:27:11"), new Priority(7), new Category(3));
    public final static Task taskOne12 = new Task(12, new User(1), "Захватить мир", 1, LocalDateTime.parse("2019-04-30T09:38:39"), new Priority(8), null);
    public final static Task taskOne13 = new Task(13, new User(1), "Помолиться", 0, LocalDateTime.parse("2020-04-27T15:27:34"), new Priority(7), new Category(4));
    public final static Task taskOne14 = new Task(14, new User(1), "Помолиться", 0, LocalDateTime.parse("2021-04-27T15:27:34"), new Priority(8), new Category(4));
    public final static Task taskTwo15 = new Task(15, new User(2), "Побрить кота", 1, LocalDateTime.parse("2018-04-28T07:03:03"), new Priority(9), null);
    public final static Task taskTwo16 = new Task(16, new User(2), "Побрить кота", 1, LocalDateTime.parse("2019-04-28T07:03:03"), new Priority(10), null);
    public final static Task taskTwo17 = new Task(17, new User(2), "Приворожить начальника", 0, LocalDateTime.parse("2020-05-06T09:38:23"), new Priority(9), new Category(5));
    public final static Task taskTwo18 = new Task(18, new User(2), "Поплакать", 0, LocalDateTime.parse("2021-05-01T12:01:18"), new Priority(10), new Category(6));

    public static Task getNewTask() {
        return new Task(null, new User(1), "New", 1, LocalDateTime.parse("2016-04-27T15:27:34"), new Priority(7), new Category(3));
    }

    public static Task getUpdatedTask() {
        Task updated = new Task(taskOne11);
        updated.setCategory(new Category(6));
        updated.setTitle("Updated");
        updated.setDate(LocalDateTime.parse("2015-04-27T15:27:34"));
        return updated;
    }


    public static ResultMatcher jsonMatcher(Task expected, BiConsumer<Task, Task> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asCategory(mvcResult), expected);
    }

    public static ResultMatcher jsonMatcher(List<Task> expected, BiConsumer<List<Task>, List<Task>> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asCategories(mvcResult), expected);
    }

    public static Task asCategory(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, Task.class);
    }

    public static List<Task> asCategories(MvcResult mvcResult) throws IOException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValues(jsonActual, Task.class);
    }

    public static void assertNoIdEquals(Task actual, Task expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(expected);
    }
    public static void assertNoIdEquals(List<Task> actual, List<Task> expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("date")
                .ignoringFieldsMatchingRegexes(".*user")
                .ignoringFieldsMatchingRegexes(".*completedCount")
                .ignoringActualNullFields()
                .isEqualTo(expected);
    }
}
