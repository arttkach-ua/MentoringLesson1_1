package com.example.ValidatorPattern.reposithory;

import com.example.ValidatorPattern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from users q1" +
            " inner join" +
            " (select * from users_languages u " +
            " WHERE" +
            " u.language_id in (select language_id from books b where id=:bookId)) q2 " +
            " ON" +
            " q1.id = q2.user_id", nativeQuery = true)
    List<User> findUsersForBook(@Param("bookId") Integer bookId);

}