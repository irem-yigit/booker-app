package com.sisterslab.bookerapp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int pageCount;
}
