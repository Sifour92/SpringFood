package ru.javawebinar.topjava.service.datajpa;


import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles(DATAJPA)
class DataJpaMealServiceTest extends AbstractMealServiceTest {
//        @Test
//    public void getWithUser() throws Exception {
//                Meal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
//                assertMatch(adminMeal, ADMIN_MEAL1);
//               UserTestData.assertMatch(adminMeal.getUser(), UserTestData.ADMIN);
//            }

    @Test
    public void getWithUserNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithUser(1, ADMIN_ID));
    }
}


