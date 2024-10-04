package com.sisterslab.goodreadsapp.model.request;

import com.sisterslab.goodreadsapp.model.Book;
import com.sisterslab.goodreadsapp.model.BookShelfType;
import com.sisterslab.goodreadsapp.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BookShelfRequestDTO {

    @Column(nullable = false)
    private String name;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookShelfType type;  // Enum alanı ekleniyor
}
