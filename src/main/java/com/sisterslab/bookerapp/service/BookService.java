package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.exception.ResourceNotFoundException;
import com.sisterslab.bookerapp.exception.ValidationException;
import com.sisterslab.bookerapp.model.dto.request.BookRequestDTO;
import com.sisterslab.bookerapp.model.dto.response.BookResponseDTO;
import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    // Add book
    public BookResponseDTO addBook(BookRequestDTO dto) {
        if (bookRepository.existsByTitleAndAuthorAndPublisher(dto.getTitle(), dto.getAuthor(),dto.getPublisher())){
            throw new ValidationException("Book already exists.");
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setIsbn(dto.getIsbn());
        book.setPageCount(dto.getPageCount());

        Book savedBook = bookRepository.save(book);
        return mapToResponseDTO(savedBook);
    }

    // List all books
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get a book by ID
    public BookResponseDTO getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToResponseDTO(book);
    }

    // Get a book by isbn
    public BookResponseDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToResponseDTO(book);
    }

    // Update book by id
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setIsbn(dto.getIsbn());
        book.setPageCount(dto.getPageCount());

        Book updatedBook = bookRepository.save(book);
        return mapToResponseDTO(updatedBook);
    }

    // Delete book by id
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }

    // Manual DTO conversion
    private BookResponseDTO mapToResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setIsbn(book.getIsbn());
        dto.setPageCount(book.getPageCount());
        return dto;
    }
}
