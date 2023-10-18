package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudUserRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
//        if (meal.getId() != null) {
//            // Проверка на существование сущности по ID и userId
//            if (!repository.existsByIdAndUserId(meal.getId(), userId)) {
//                return null;
//            }
//        }

        User user = repository.findByUserId(userId);
        System.out.println(user);
//        meal.setUser(new User(userId)); // Здесь предполагается, что User - это сущность пользователя.

        // В Spring Data JPA уже есть методы для сохранения и обновления сущностей.
//        return mealRepository.save(meal);
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null;
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {
        return null;
    }
}
