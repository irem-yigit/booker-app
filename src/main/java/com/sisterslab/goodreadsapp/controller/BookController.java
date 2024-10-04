package com.sisterslab.goodreadsapp.controller;

import com.sisterslab.goodreadsapp.model.Book;
import com.sisterslab.goodreadsapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Kitap ekleme
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    //Tüm kitapları listeleme
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //Belirli bir kitabı id ile getirme
    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    //Belirli bir kitabı isbn ile getirme
    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    //Kitap güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) throws Exception {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    //Kitap silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
