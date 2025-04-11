/*package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.model.dto.request.BookRequestDTO;
import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void addBook_BookAlreadyExists_ThrowsValidationException() {
        // Given
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Book Title");
        bookRequestDTO.setAuthor("Book Author");
        bookRequestDTO.setPublisher("Book Publisher");
        bookRequestDTO.setIsbn("1234567890123");
        bookRequestDTO.setPageCount(250);

        when(bookRepository.existsByIsbn(bookRequestDTO.getIsbn())).thenReturn(true);

        // When
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            bookService.addBook(bookRequestDTO);
        });

        // Then
        assertEquals("This ISBN is already registered", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    public void addBook_NewBook_SavesAndReturnsBook() {
        // Given
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("New Book Title");
        bookRequestDTO.setAuthor("New Book Author");
        bookRequestDTO.setPublisher("New Book Publisher");
        bookRequestDTO.setIsbn("1234567890123");
        bookRequestDTO.setPageCount(350);

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublisher(bookRequestDTO.getPublisher());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPageCount(bookRequestDTO.getPageCount());

        when(bookRepository.existsByIsbn(bookRequestDTO.getIsbn())).thenReturn(false);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // When
        Book savedBook = bookService.addBook(bookRequestDTO);

        // Then
        assertNotNull(savedBook);
        assertEquals(bookRequestDTO.getIsbn(), savedBook.getIsbn());
        verify(bookRepository).save(any(Book.class));
    }
}*/
