package com.sisterslab.goodreadsapp.service;

import com.sisterslab.goodreadsapp.exception.BookNotFoundException;
import com.sisterslab.goodreadsapp.exception.BookShelfNotFoundException;
import com.sisterslab.goodreadsapp.exception.UserNotFoundException;
import com.sisterslab.goodreadsapp.model.Book;
import com.sisterslab.goodreadsapp.model.BookShelf;
import com.sisterslab.goodreadsapp.model.BookShelfType;
import com.sisterslab.goodreadsapp.model.User;
import com.sisterslab.goodreadsapp.repository.BookRepository;
import com.sisterslab.goodreadsapp.repository.BookShelfRepository;
import com.sisterslab.goodreadsapp.repository.UserRepository;
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
    public BookShelf createBookShelf(User user, String name, BookShelfType type) throws Exception {
        User foundUser = userRepository.findByUserName(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

        BookShelf bookShelf = new BookShelf();
        bookShelf.setName(name);
        bookShelf.setUser(user);
        bookShelf.setType(type);

        return bookShelfRepository.save(bookShelf);
    }
    /*public ReadingList createReadingList(String username, String name) throws Exception {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new Exception("Kullanıcı bulunamadı"));

        ReadingList readingList = new ReadingList();
        readingList.setName(name);
        readingList.setUser(user);

        return readingListRepository.save(readingList);
    }*/

    //Kullanıcının Kitap listesini getirme işlemi
    public List<BookShelf> getUserBookShelf(String username, BookShelfType type) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

        return bookShelfRepository.findByUserAndType(user, type);   // Liste türüne göre filtreleme
    }

    //Okuma listesine kitap ekleme
    public BookShelf addBookToBookShelf(Long bookShelfId, Long bookId) throws Exception {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId)
                .orElseThrow(() -> new BookShelfNotFoundException("Okuma listesi bulunamadı"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Kitap bulunamadı"));

        bookShelf.getBooks().add(book);
        return bookShelfRepository.save(bookShelf);
    }

    //Okuma Listesinden kitap silme
    public BookShelf removeBookFromBookShelf(Long bookShelfId, Long bookId) throws Exception {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId)
                .orElseThrow(() -> new BookShelfNotFoundException("Okuma listesi bulunamadı"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Kitap bulunamadı"));

        bookShelf.getBooks().remove(book);
        return bookShelfRepository.save(bookShelf);
    }

    /*public Object getUserBookShelfByType(String username, BookShelfType type) {
    }*/
}
