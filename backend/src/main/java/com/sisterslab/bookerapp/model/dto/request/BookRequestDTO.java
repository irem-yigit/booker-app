package com.sisterslab.bookerapp.model.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

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
    @Size(min = 13, max = 13, message = "ISBN must be exactly 13 characters")
    @Pattern(regexp = "^[0-9X]*$", message = "ISBN must only contain numbers and 'X'")
    private String isbn;

    @NotNull(message = "Page count cannot be null")
    @Min(value = 1, message = "Page count must be at least 1")
    private int pageCount;
}
