package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Override
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
//    @Query(User.DELETE)
    int delete(@Param("id") int id);

    User getByEmail(String email);

//    @Override
//    Optional<User> findById(Integer id);
    @Override
    List<User> findAll(Sort sort);



    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.meals WHERE u.id = ?1")
    User getWithMeals(int id);

}
