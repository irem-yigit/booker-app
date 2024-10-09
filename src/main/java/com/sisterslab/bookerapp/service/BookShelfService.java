package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.exception.BookNotFoundException;
import com.sisterslab.bookerapp.exception.BookShelfNotFoundException;
import com.sisterslab.bookerapp.exception.UserNotFoundException;
import com.sisterslab.bookerapp.model.Book;
import com.sisterslab.bookerapp.model.BookShelf;
import com.sisterslab.bookerapp.model.BookShelfType;
import com.sisterslab.bookerapp.model.User;
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

    //Okuma Listesi oluşturma
    public BookShelf createBookShelf(String username, String name, BookShelfType type) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        BookShelf bookShelf = new BookShelf();
        bookShelf.setName(name);
        bookShelf.setUser(user);
        bookShelf.setType(type);

        return bookShelfRepository.save(bookShelf);
    }

    //Kullanıcının Kitap listesini, kitaplık türüne(BookShelfType) göre getirme işlemi
    public List<BookShelf> getUserBookShelf(String username, BookShelfType type) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return bookShelfRepository.findByUserAndType(user, type);   // Liste türüne göre filtreleme
    }

    //Okuma listesine kitap ekleme
    public BookShelf addBookToBookShelf(Long bookShelfId, Long bookId) throws Exception {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId)
                .orElseThrow(() -> new BookShelfNotFoundException("Bookshelf not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found."));

        bookShelf.getBooks().add(book);
        return bookShelfRepository.save(bookShelf);
    }

    //Okuma Listesinden kitap silme
    public BookShelf removeBookFromBookShelf(Long bookShelfId, Long bookId) throws Exception {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId)
                .orElseThrow(() -> new BookShelfNotFoundException("Bookshelf not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found."));

        bookShelf.getBooks().remove(book);
        return bookShelfRepository.save(bookShelf);
    }

    /*public Object getUserBookShelfByType(String username, BookShelfType type) {
    }*/
}
