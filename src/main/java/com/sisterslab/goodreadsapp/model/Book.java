package com.sisterslab.goodreadsapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private String author;

    @NotNull(message = "Publisher cannot be null")
    private String publisher;

    @NotNull(message = "Isbn cannot be null")
    @Column(unique = true)          //Bu kodun benzersiz olması için bu kodu kullandım. Yani aynı isbnden bir tane daha olmamalı
    private String isbn;            // TODO: International Standard Book Number --> 13 haneli olmak zorunda sorgusu eklenmeli!!

    @NotNull(message = "Page count cannot be null")
    private int pageCount;
}
