package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.dto.request.BookRequestDTO;
import com.sisterslab.bookerapp.model.dto.response.BookResponseDTO;
import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO addedbook = bookService.addBook(bookRequestDTO);
        return new ResponseEntity<>(addedbook, HttpStatus.CREATED);
    }

    //READ - List all books
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //READ - Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    //READ - Get a book by isbn
    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    //UPDATE - Update book by id
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.ok(bookService.updateBook(id, bookRequestDTO));
    }

    //DELETE - Delete book by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}