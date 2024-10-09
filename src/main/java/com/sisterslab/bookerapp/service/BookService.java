package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.exception.BookNotFoundException;
import com.sisterslab.bookerapp.exception.DublicateBookException;
import com.sisterslab.bookerapp.model.Book;
import com.sisterslab.bookerapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        if (bookRepository.existsByTitleAndAuthorAndPublisher(book.getTitle(), book.getAuthor(),book.getPublisher())){
            throw new DublicateBookException("Book already exists.");
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow();
    }

    public Book getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow();
    }

    public Book updateBook(Long id, Book bookDetails) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublisher(bookDetails.getPublisher());
        book.setIsbn(bookDetails.getIsbn());                //TODO: !! isbn güncellenmeli mi?
        book.setPageCount(bookDetails.getPageCount());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
}
