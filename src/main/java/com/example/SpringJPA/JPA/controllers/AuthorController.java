package com.example.SpringJPA.JPA.controllers;

import com.example.SpringJPA.JPA.domain.dto.AuthorDto;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.mappers.AuthorMapper;
import com.example.SpringJPA.JPA.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    public void setAuthorService(AuthorService authorService,AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }
//    CREATE METHOD TO ACCEPT BOOKS
      @PostMapping(path = "/authors")
      public AuthorDto CreateAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return authorMapper.mapTo(savedAuthorEntity);
      }
//      WE ALSO NEED A SERVICE LAYER TO CONNECT THIS PRESENTATION LAYER TO THE SERVICE LAYER

}
