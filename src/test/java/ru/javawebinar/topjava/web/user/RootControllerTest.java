package ru.javawebinar.topjava.web.user;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;

class RootControllerTest extends AbstractControllerTest {
//
//    @Test
//    public void getUsers() throws Exception {
//        mockMvc.perform(get("/users")) //тип запроса и урл
//                .andDo(print())//распечатать
//                .andExpect(status().isOk()) //ожидает статус 200
//                .andExpect(view().name("users"))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
//                .andExpect(model().attribute("users",
//                        new AssertionMatcher<List<User>>() {
//                            @Override
//                            public void assertion(List<User> actual) throws AssertionError {
//                                assertMatch(actual, ADMIN, USER);
//                            }
//                        }
//                ));
//    }



//    @GetMapping("/meals")
//    public String getMeals(Model model) {
//        model.addAttribute("meals",
//                MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
//        return "meals";
//    }


    @Test
    public void getMeals() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", getTos(MEALS, SecurityUtil.authUserCaloriesPerDay())));
    }
}
