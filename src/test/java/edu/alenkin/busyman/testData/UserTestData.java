package edu.alenkin.busyman.testData;

import edu.alenkin.busyman.model.Role;
import edu.alenkin.busyman.model.User;

import java.time.LocalDateTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class UserTestData {
    public final static int userId = 1;
    public final static int adminId = 2;
    public static final User user = new User(userId, "User", "user@yandex.ru",
            "password", true, LocalDateTime.parse("2020-04-29T15:27:11"), 2, 2, Role.USER);

    public static final User admin = new User(adminId, "Admin", "admin@gmail.com",
            "admin", true, LocalDateTime.parse("2020-04-29T15:27:11"), 2, 2, Role.USER, Role.ADMIN);

    public static User getNew(){
        return new User(null, "New", "new@gmail.com",
                "new", true, LocalDateTime.parse("2020-04-29T15:27:11"), 0, 0, Role.USER);
    }

    public static User getUpdatedUser() {
        User updated = new User(user);
        updated.setName("updated");
        updated.setEmail("updated@gmail.com");
        updated.setPassword("updated");
        return updated;
    }
}
