package com.sisterslab.bookerapp.repository;

import com.sisterslab.bookerapp.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleAndAuthorAndPublisher(String title, String author, String publisher);

    Optional<Book> findByIsbn(String isbn);

}
