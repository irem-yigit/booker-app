package com.sisterslab.bookerapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    @Column(name = "book_title", nullable = false, length = 50)
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    @Column(name = "book_author", nullable = false, length = 50)
    private String author;

    @NotBlank(message = "Publisher cannot be blank")
    @Column(name = "book_publisher", nullable = false)
    private String publisher;

    @NotNull(message = "Isbn cannot be null")
    @Size(min = 13, max = 13, message = "ISBN must be between 13 characters")
    @Pattern(regexp = "^[0-9X]*$", message = "ISBN must only contain numbers and 'X'")
    @Column(name = "book_isbn", nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "Page count cannot be null")
    @Min(value = 1, message = "Page count must be at least 1")
    @Column(name = "book_pagecount", nullable = false)
    private int pageCount;

}
