package edu.alenkin.busyman.testData;

import edu.alenkin.busyman.model.Priority;
import edu.alenkin.busyman.model.User;

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
}
