package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.exception.ResourceNotFoundException;
import com.sisterslab.bookerapp.exception.ValidationException;
import com.sisterslab.bookerapp.model.dto.request.BookRequestDTO;
import com.sisterslab.bookerapp.model.dto.response.BookResponseDTO;
import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

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
        BookRequestDTO bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Existing Title");
        bookRequest.setAuthor("Author");
        bookRequest.setPublisher("Publisher");
        bookRequest.setIsbn("1234567890123");
        bookRequest.setPageCount(200);

        when(bookRepository.existsByTitleAndAuthorAndPublisher(
                bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublisher()))
                .thenReturn(true);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            bookService.addBook(bookRequest);
        });

        assertEquals("Book already exists.", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    public void addBook_ValidBook_SavesAndReturnsResponseDTO() {
        BookRequestDTO bookRequest = new BookRequestDTO();
        bookRequest.setTitle("New Book");
        bookRequest.setAuthor("Author");
        bookRequest.setPublisher("Publisher");
        bookRequest.setIsbn("1234567890123");
        bookRequest.setPageCount(200);

        Book book = new Book();
        book.setId(1L);
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
        book.setIsbn(bookRequest.getIsbn());
        book.setPageCount(bookRequest.getPageCount());

        when(bookRepository.existsByTitleAndAuthorAndPublisher(any(), any(), any())).thenReturn(false);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookResponseDTO response = bookService.addBook(bookRequest);

        assertNotNull(response);
        assertEquals("New Book", response.getTitle());
        assertEquals("1234567890123", response.getIsbn());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void getBookById_BookExists_ReturnsResponseDTO() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample");
        book.setAuthor("Author");
        book.setPublisher("Publisher");
        book.setIsbn("1234567890123");
        book.setPageCount(100);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponseDTO result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Sample", result.getTitle());
    }

    @Test
    public void getBookById_NotFound_ThrowsException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(99L);
        });
    }

    @Test
    public void getAllBooks_ReturnsBookList() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author");
        book1.setPublisher("Publisher");
        book1.setIsbn("1111111111111");
        book1.setPageCount(100);

        when(bookRepository.findAll()).thenReturn(List.of(book1));

        List<BookResponseDTO> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Book 1", result.get(0).getTitle());
    }

    @Test
    public void updateBook_BookExists_UpdatesAndReturnsResponseDTO() {
        Long bookId = 1L;

        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Old Title");
        existingBook.setAuthor("Old Author");
        existingBook.setPublisher("Old Publisher");
        existingBook.setIsbn("1234567890123");
        existingBook.setPageCount(100);

        BookRequestDTO updateRequest = new BookRequestDTO();
        updateRequest.setTitle("New Title");
        updateRequest.setAuthor("New Author");
        updateRequest.setPublisher("New Publisher");
        updateRequest.setIsbn("9876543210987");
        updateRequest.setPageCount(300);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookResponseDTO updatedBook = bookService.updateBook(bookId, updateRequest);

        assertNotNull(updatedBook);
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("9876543210987", updatedBook.getIsbn());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void updateBook_BookNotFound_ThrowsException() {
        Long bookId = 99L;
        BookRequestDTO updateRequest = new BookRequestDTO();
        updateRequest.setTitle("Updated Title");

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBook(bookId, updateRequest);
        });

        verify(bookRepository, never()).save(any());
    }

    @Test
    public void deleteBook_BookExists_DeletesBook() {
        Long bookId = 1L;

        Book book = new Book();
        book.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        assertDoesNotThrow(() -> bookService.deleteBook(bookId));
        verify(bookRepository).delete(book);
    }

    @Test
    public void deleteBook_BookNotFound_ThrowsException() {
        Long bookId = 404L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(bookId);
        });

        verify(bookRepository, never()).delete(any());
    }

}
