package com.example.SpringJPA.JPA.controllers;

import com.example.SpringJPA.JPA.TestDataUtil;
import com.example.SpringJPA.JPA.domain.dto.AuthorDto;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.services.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnHttp201Created() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthorA();
        testAuthorA.setId(null);
        String authorJsonA=objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJsonA)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorA.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorA.getAge())
        );
    }

    @Test
    public void testThatfindAllAuthorSuccessfullyReturnHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

//    IDX ISSUE
//    @Test
//    public void testThatFindAllSuccessfullyReturnsAllAuthors() throws Exception {
//        AuthorEntity testAuthorA = TestDataUtil.createAuthorA();
//        testAuthorA=authorService.createAuthor(testAuthorA);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].name").value(testAuthorA.getName())
//        );
//    }

    @Test
    public void testThatFindAuthorByIdSuccessfullyReturnHttp200() throws Exception {
        AuthorEntity testAuthorEntity= TestDataUtil.createAuthorB();
        testAuthorEntity=authorService.createAuthor(testAuthorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/" + testAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(testAuthorEntity.getId())
        );
    }

    @Test
    public void testThatUpdateAuthorSuccessfullyReturnHttp200() throws Exception {
        AuthorEntity testAuthorEntity= TestDataUtil.createAuthorA();
        testAuthorEntity=authorService.createAuthor(testAuthorEntity);

        AuthorDto testAuthorDto = TestDataUtil.createAuthorDtoA();
        String testAuthorJson=objectMapper.writeValueAsString(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + testAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testAuthorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDto.getAge())
        );
    }


}
