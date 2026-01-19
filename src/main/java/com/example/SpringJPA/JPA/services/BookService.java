package com.example.SpringJPA.JPA.services;

import com.example.SpringJPA.JPA.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn,BookEntity bookEntity);
}
