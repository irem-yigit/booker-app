package com.sisterslab.bookerapp.repository;

import com.sisterslab.bookerapp.model.BookShelf;
import com.sisterslab.bookerapp.model.BookShelfType;
import com.sisterslab.bookerapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    //List<BookShelf> findByUser(User user);
    List<BookShelf> findByUserAndType(User user, BookShelfType type);  // Find by BookShelf type
}
