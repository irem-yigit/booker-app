package com.sisterslab.bookerapp.model.request;

import com.sisterslab.bookerapp.model.Book;
import com.sisterslab.bookerapp.model.BookShelfType;
import com.sisterslab.bookerapp.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BookShelfRequestDTO {

    @NotBlank(message = "BookShelf name cannot be blank")
    private String name;

    @NotBlank(message = "Username cannot be blank")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "BookShelf_books",
            joinColumns = @JoinColumn(name = "BookShelf_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    @NotNull(message = "BookShelf type cannot be null")
    @Enumerated(EnumType.STRING)
    private BookShelfType type;
}
