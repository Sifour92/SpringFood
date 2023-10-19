package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal,Integer> {
    @Transactional
    @Override
    Meal save(Meal meal);
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findByUserId(@Param("userId") int userId);
//    User findByUserById(int userId);

    boolean existsByIdAndUserId(Integer id, int userId);

    Meal findByIdAndUserId(Integer id, int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id = :id AND m.user.id = :userId")
    int deleteByIdAndUserId(@Param("id") Integer id, @Param("userId") int userId);

    List<Meal> findAllByUserId(int userId, Sort sort);
    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
