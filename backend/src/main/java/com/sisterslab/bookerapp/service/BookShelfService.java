package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.exception.ResourceNotFoundException;
import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.model.entity.BookShelf;
import com.sisterslab.bookerapp.model.enums.BookShelfType;
import com.sisterslab.bookerapp.model.entity.User;
import com.sisterslab.bookerapp.repository.BookRepository;
import com.sisterslab.bookerapp.repository.BookShelfRepository;
import com.sisterslab.bookerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShelfService {

    private final BookShelfRepository bookShelfRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookShelfService(BookShelfRepository bookShelfRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.bookShelfRepository = bookShelfRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public BookShelf createBookShelf(String username, String name, BookShelfType type) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        BookShelf bookShelf = new BookShelf();
        bookShelf.setName(name);
        bookShelf.setUser(user);
        bookShelf.setType(type);

        return bookShelfRepository.save(bookShelf);
    }

    public List<BookShelf> getUserBookShelf(String username, BookShelfType type) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        return bookShelfRepository.findByUserAndType(user, type);   // Filter by list type
    }

    public BookShelf addBookToBookShelf(Long bookShelfId, Long bookId) throws Exception {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bookshelf not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found."));

        bookShelf.getBooks().add(book);
        return bookShelfRepository.save(bookShelf);
    }

    public BookShelf removeBookFromBookShelf(Long bookShelfId, Long bookId) throws Exception {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bookshelf not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found."));

        bookShelf.getBooks().remove(book);
        return bookShelfRepository.save(bookShelf);
    }

}
