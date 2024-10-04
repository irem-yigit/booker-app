package com.sisterslab.goodreadsapp.service;

import com.sisterslab.goodreadsapp.exception.BookNotFoundException;
import com.sisterslab.goodreadsapp.exception.DublicateBookException;
import com.sisterslab.goodreadsapp.model.Book;
import com.sisterslab.goodreadsapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DuplicatesPredicate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //Yeni kitap ekleme
    public Book addBook(Book book) {
        if (bookRepository.existsByTitleAndAuthorAndPublisher(book.getTitle(), book.getAuthor(),book.getPublisher())){
            throw new DublicateBookException("Book already exists.");
        }
        return bookRepository.save(book);
    }

    //Tüm kitapları listeleme
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Belirli bir kitabı id ile getirme
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow();
    }

    //Belirli bir kitabı isbn ile getirme
    public Book getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow();
    }

    //Kitap güncelleme
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

    //Kitap silme
    public void deleteBook(Long id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
}
