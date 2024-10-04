package com.sisterslab.goodreadsapp.controller;

import com.sisterslab.goodreadsapp.model.BookShelf;
import com.sisterslab.goodreadsapp.model.BookShelfType;
import com.sisterslab.goodreadsapp.model.request.BookShelfRequestDTO;
import com.sisterslab.goodreadsapp.service.BookShelfService;
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

    //Yeni okuma listesi oluşturma
    @PostMapping("/add")
    public ResponseEntity<BookShelf> createBookShelf(@Valid @RequestParam String username, @RequestParam String name, @RequestParam BookShelfType type) throws Exception {
        BookShelf bookShelf = bookShelfService.createBookShelf(username, name, type);
        return ResponseEntity.ok(bookShelf);
    }

    //Kullanıcının Kitap listesini, kitaplık türüne(BookShelfType) göre getirme işlemi
    @GetMapping("/{username}/{type}")
    public ResponseEntity<List<BookShelf>> getUserBookShelfByType(@PathVariable String username, @PathVariable BookShelfType type) throws Exception {
        return ResponseEntity.ok(bookShelfService.getUserBookShelf(username, type));
    }

    //Okuma listesine kitap ekleme
    @PostMapping("/{bookshelfId}/books/{bookId}")
    public ResponseEntity<BookShelf> addBookToBookShelf(
            @Valid @PathVariable Long bookShelfId, @PathVariable Long bookId) throws Exception {
        return ResponseEntity.ok(bookShelfService.addBookToBookShelf(bookShelfId, bookId));
    }

    //Okuma Listesinden kitap silme
    @DeleteMapping("/{bookshelfId}/books/{bookId}")
    public ResponseEntity<BookShelf> removeBookFromBookShelf(
            @PathVariable Long bookShelfId, @PathVariable Long bookId) throws Exception {
        return ResponseEntity.ok(bookShelfService.removeBookFromBookShelf(bookShelfId, bookId));
    }


}
