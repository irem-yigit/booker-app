package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //CREATE - Add book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    //READ - List all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //READ - List a book by ID
    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    //READ - List a book by isbn
    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    //UPDATE - Update book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id, @RequestBody Book bookDetails) throws Exception {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    //DELETE - Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}