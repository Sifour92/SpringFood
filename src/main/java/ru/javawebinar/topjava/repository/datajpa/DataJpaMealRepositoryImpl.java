package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private static final Sort SORT_NAME_DATE_TIME = Sort.by("dateTime").descending();
    @Autowired
    private CrudMealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getId() != null) {

            if (!repository.existsByIdAndUserId(meal.getId(), userId)) {
                return null;
            }
        }
        User user = repository.findByUserId(userId);
//        User user = repository.findByUserById(userId);
        meal.setUser(user);
        return repository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.deleteByIdAndUserId(id,userId)!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.findByIdAndUserId(id,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.findAllByUserId(userId,SORT_NAME_DATE_TIME);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {

        LocalDateTime startDateTime = getStartInclusive(startDate);
        LocalDateTime endDateTime = getEndExclusive(endDate);
        return repository.getBetween(startDateTime, endDateTime, userId);
    }
}
