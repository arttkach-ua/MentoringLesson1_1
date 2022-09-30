package com.example.ValidatorPattern.reposithory;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select * from books b WHERE" +
            " b.id not in (select book_id from user_books where user_id=:userId) and" +
            " b.language_id in (select language_id from users_languages where user_id=:userId)", nativeQuery = true)
    List<Book> findUnleadedBooks(@Param("userId") Integer userId);

    @Modifying
    @Query(value = "insert into user_books(user_id, user_id) values (:userId,:bookId)", nativeQuery = true)
    boolean saveBookAsRead(@Param("bookId") Integer bookId, @Param("userId") Integer userId);


}