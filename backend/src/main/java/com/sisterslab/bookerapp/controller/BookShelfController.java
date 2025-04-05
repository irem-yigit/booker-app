package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.BookShelf;
import com.sisterslab.bookerapp.model.BookShelfType;
import com.sisterslab.bookerapp.service.BookShelfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookshelf")
public class BookShelfController {

    private final BookShelfService bookShelfService;

    @Autowired
    public BookShelfController(BookShelfService bookShelfService) {
        this.bookShelfService = bookShelfService;
    }

    //CREATE - Add bookShelf
    @PostMapping
    public ResponseEntity<BookShelf> createBookShelf(@Valid @RequestParam String username, @RequestParam String name, @RequestParam BookShelfType type) throws Exception {
        BookShelf bookShelf = bookShelfService.createBookShelf(username, name, type);
        return ResponseEntity.ok(bookShelf);
    }

    //READ - List User's Book list by BookShelfType
    @GetMapping("/{username}/{type}")
    public ResponseEntity<List<BookShelf>> getUserBookShelfByType(@PathVariable String username, @PathVariable BookShelfType type) throws Exception {
        return ResponseEntity.ok(bookShelfService.getUserBookShelf(username, type));
    }

    //CREATE - Add book to the BookShelf
    @PostMapping("/{bookshelfId}/books/{bookId}")
    public ResponseEntity<BookShelf> addBookToBookShelf(
            @Valid @PathVariable Long bookShelfId, @PathVariable Long bookId) throws Exception {
        return ResponseEntity.ok(bookShelfService.addBookToBookShelf(bookShelfId, bookId));
    }

    //DELETE - Delete book from the BookShelf
    @DeleteMapping("/{bookshelfId}/books/{bookId}")
    public ResponseEntity<BookShelf> removeBookFromBookShelf(
            @PathVariable Long bookShelfId, @PathVariable Long bookId) throws Exception {
        return ResponseEntity.ok(bookShelfService.removeBookFromBookShelf(bookShelfId, bookId));
    }


}
