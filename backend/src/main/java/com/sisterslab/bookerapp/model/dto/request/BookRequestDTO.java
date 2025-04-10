package com.sisterslab.bookerapp.model.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private String author;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    @NotNull(message = "Isbn cannot be null")
    @Size(message = "ISBN must be between 13 characters")
    private String isbn;

    @NotNull(message = "Page count cannot be null")
    private int pageCount;
}
