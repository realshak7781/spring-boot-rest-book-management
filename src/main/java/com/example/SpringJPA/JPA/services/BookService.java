package com.example.SpringJPA.JPA.services;

import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import java.util.List;

public interface BookService {
    BookEntity createBook(String isbn,BookEntity bookEntity);
    List<BookEntity> findAll();
}
