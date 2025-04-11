package com.sisterslab.bookerapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisterslab.bookerapp.model.dto.request.BookRequestDTO;
import com.sisterslab.bookerapp.model.dto.response.BookResponseDTO;
import com.sisterslab.bookerapp.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookRequestDTO bookRequest;
    private BookResponseDTO bookResponse;

    @BeforeEach
    void setUp() {
        bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Test Book");
        bookRequest.setAuthor("Test Author");
        bookRequest.setPublisher("Test Publisher");
        bookRequest.setIsbn("1234567890123");
        bookRequest.setPageCount(300);

        bookResponse = new BookResponseDTO();
        bookResponse.setId(1L);
        bookResponse.setTitle("Test Book");
        bookResponse.setAuthor("Test Author");
        bookResponse.setPublisher("Test Publisher");
        bookResponse.setIsbn("1234567890123");
        bookResponse.setPageCount(300);
    }

    @Test
    void testAddBook() throws Exception {
        when(bookService.addBook(any(BookRequestDTO.class))).thenReturn(bookResponse);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookResponse.getId()))
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<BookResponseDTO> books = Arrays.asList(bookResponse);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookResponse);

        mockMvc.perform(get("/api/books/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    void testGetBookByIsbn() throws Exception {
        when(bookService.getBookByIsbn("1234567890123")).thenReturn(bookResponse);

        mockMvc.perform(get("/api/books/isbn/1234567890123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    void testUpdateBook() throws Exception {
        when(bookService.updateBook(Mockito.eq(1L), any(BookRequestDTO.class))).thenReturn(bookResponse);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("1234567890123"));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }
}
