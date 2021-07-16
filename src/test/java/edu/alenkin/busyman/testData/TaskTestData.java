package edu.alenkin.busyman.testData;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
