package com.sisterslab.goodreadsapp.repository;

import com.sisterslab.goodreadsapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleAndAuthorAndPublisher(String title, String author, String publisher);
    Optional<Book> findByIsbn(String isbn);
}
