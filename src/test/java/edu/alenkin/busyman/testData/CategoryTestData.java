package edu.alenkin.busyman.testData;

import edu.alenkin.busyman.model.Category;
import edu.alenkin.busyman.model.User;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class CategoryTestData {
    public final static int familyId = 3;
    public final static int workId = 4;
    public final static int chillId = 5;
    public final static int travelId = 6;
    public static Category family = new Category(familyId, new User(1), "Семья", 1, 0);
    public static Category work = new Category(workId, new User(1), "Работа", 0, 2);
    public static Category chill = new Category(chillId, new User(2), "Отдых", 0, 1);
    public static Category travel = new Category(travelId, new User(2), "Путешествия", 0, 1);

    public static Category getNewCategory() {
        return new Category(null, new User(1), "New", 0, 0);
    }

    public static Category getUpdatedCategory() {
        Category forUpdate = new Category(family);
        forUpdate.setUser(new User(2));
        forUpdate.setTitle("New");
        return forUpdate;
    }
}
