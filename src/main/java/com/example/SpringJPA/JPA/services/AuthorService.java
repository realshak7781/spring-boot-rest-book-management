package com.example.SpringJPA.JPA.services;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;

import java.util.List;

//THIS IS THE SERVICE LAYER THAT CONNECTS THE PERSISTENCE LAYER REPOSITORIES TO  PRESENTATION LAYER CONTROLLERS
public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity  authorEntity);
    List<AuthorEntity> findAll();
}
