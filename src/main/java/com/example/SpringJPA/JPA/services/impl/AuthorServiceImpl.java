package com.example.SpringJPA.JPA.services.impl;

import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.repositories.AuthorRepository;
import com.example.SpringJPA.JPA.services.AuthorService;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
