package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import ru.javawebinar.topjava.model.AbstractBaseEntity;
public class SecurityUtil {
    private SecurityUtil() {
            }
    private static int id = AbstractBaseEntity.START_SEQ;

    public static int authUserId() {

        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }


    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}