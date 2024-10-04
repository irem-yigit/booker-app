package com.sisterslab.goodreadsapp.repository;

import com.sisterslab.goodreadsapp.model.BookShelf;
import com.sisterslab.goodreadsapp.model.BookShelfType;
import com.sisterslab.goodreadsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    List<BookShelf> findByUser(User user);
    List<BookShelf> findByUserAndType(User user, BookShelfType type);  // Liste türüne göre sorgu
}
