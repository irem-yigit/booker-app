package com.sisterslab.goodreadsapp.model.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookRequestDTO {

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    @Column(name = "book_title", nullable = false, length = 50)
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    @Column(name = "book_author", nullable = false, length = 50)
    private String author;

    @NotNull(message = "Publisher cannot be null")
    @Column(name = "book_publisher", nullable = false)
    private String publisher;

    @NotNull(message = "Isbn cannot be null")
    @Size(message = "ISBN must be between 13 characters")
    @Column(name = "book_isbn", nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "Page count cannot be null")
    @Column(name = "book_pagecount", nullable = false)
    private int pageCount;
}
