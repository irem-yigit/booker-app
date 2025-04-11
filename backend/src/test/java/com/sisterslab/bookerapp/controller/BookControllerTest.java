/*package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.dto.request.BookRequestDTO;
import com.sisterslab.bookerapp.model.entity.Book;
import com.sisterslab.bookerapp.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void addBook_ReturnsCreatedBook() throws Exception {
        // Given
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Book Title");
        bookRequestDTO.setAuthor("Book Author");
        bookRequestDTO.setPublisher("Book Publisher");
        bookRequestDTO.setIsbn("1234567890123");
        bookRequestDTO.setPageCount(300);

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublisher(bookRequestDTO.getPublisher());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPageCount(bookRequestDTO.getPageCount());

        when(bookService.addBook(any(BookRequestDTO.class))).thenReturn(book);

        // When & Then
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Book Title\", \"author\":\"Book Author\", \"publisher\":\"Book Publisher\", \"isbn\":\"1234567890123\", \"pageCount\":300}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book Title"))
                .andExpect(jsonPath("$.isbn").value("1234567890123"));
    }
}
*/