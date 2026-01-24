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
        BookEntity testBookEntity=TestDataUtil.createBookA(null);
        testBookEntity=bookService.createUpdateBook(testBookEntity.getIsbn(),testBookEntity);

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
        bookService.createUpdateBook(testBookC.getIsbn(), testBookC);

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

    @Test
    public void testThatFindBookByIsbnSuccessfullyReturnHttp200() throws Exception {
        BookEntity testBookC = TestDataUtil.createBookC(null);
        testBookC=bookService.createUpdateBook(testBookC.getIsbn(), testBookC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + testBookC.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBookC.getIsbn())
        );
    }

    @Test
    public void testThatFullUpateBookSuccessfullyReturn200Ok() throws Exception {
//        we are checking the data already exists and should return ok
         BookEntity testBookD = TestDataUtil.createBookD(null);
         testBookD=bookService.createUpdateBook(testBookD.getIsbn(), testBookD);
         BookDto testBookDto=TestDataUtil.createBookDtoD(null);

         String bookDtoJson=objectMapper.writeValueAsString(testBookDto);

         mockMvc.perform(
                 MockMvcRequestBuilders.put("/books/" + testBookDto.getIsbn())
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(bookDtoJson)
         ).andExpect(
                 MockMvcResultMatchers.status().isOk()
         ).andExpect(
                 MockMvcResultMatchers.jsonPath("$.isbn").value(testBookDto.getIsbn())
         ).andExpect(
                 MockMvcResultMatchers.jsonPath("$.title").value(testBookDto.getTitle())
         );
    }


    @Test
    public void testThatPartialUpdateBookSuccessfullyReturn200Ok() throws Exception {
        BookEntity testBookEntity = TestDataUtil.createBookE(null);
        bookService.createUpdateBook(testBookEntity.getIsbn(), testBookEntity);

        BookDto testBookDto=TestDataUtil.createBookDtoE(null);
        String testBookDtoJson=objectMapper.writeValueAsString(testBookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + testBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testBookDtoJson)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testBookDto.getTitle())
        );
    }
}

