package com.example.SpringJPA.JPA.controllers;


import com.example.SpringJPA.JPA.TestDataUtil;
import com.example.SpringJPA.JPA.domain.dto.BookDto;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import com.example.SpringJPA.JPA.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private BookService bookService;


    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper,BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnHttp201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createBookDtoA(null);
        String bookEntityJsonA = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookEntityJsonA)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                );
    }


    @Test
    public void testThatCreateBookSuccessfullyCreatedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createBookDtoA(null);
        String bookEntityJsonA = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookEntityJsonA)
                )
                .andExpect(
                       MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
                );
    }

    @Test
    public void testThatGetAllBooksSuccessfullyReturnHttp200Ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindAllSuccessfullyReturnsAllBooks() throws Exception {
        BookEntity testBookC = TestDataUtil.createBookC(null);
        bookService.createBook(testBookC.getIsbn(), testBookC);

        List<BookEntity> books = bookService.findAll();
        int idx=-1;
//        finding the idx in the list of books
        for(int j=0;j<books.size();j++){
            if(books.get(j).getIsbn().equals(testBookC.getIsbn())){
                idx=j;
                break;
            }
        }

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
//                position matters as the testBookC can be present anywhere in the List books
                MockMvcResultMatchers.jsonPath("$[" + idx + "].isbn").value(testBookC.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[" + idx + "].title").value(testBookC.getTitle())
        );
    }
}
