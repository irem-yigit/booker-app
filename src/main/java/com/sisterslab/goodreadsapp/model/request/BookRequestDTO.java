package com.sisterslab.goodreadsapp.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookRequestDTO {

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private String author;

    @NotNull(message = "Publisher cannot be null")
    private String publisher;

    @NotNull(message = "Isbn cannot be null")
    private String isbn;        //International Standard Book Number --> 13 haneli olmak zorunda

    @NotNull(message = "Page count cannot be null")
    private int pageCount;
}
