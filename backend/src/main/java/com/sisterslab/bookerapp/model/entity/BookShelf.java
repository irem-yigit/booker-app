package com.sisterslab.bookerapp.model.entity;

import com.sisterslab.bookerapp.model.enums.BookShelfType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookshelf")
@Getter
@Setter
public class BookShelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "BookShelf name cannot be blank")
    @Column(name = "bookshelf_name", nullable = false,length = 50)
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
    @Column(nullable = false)
    private BookShelfType type;
}
