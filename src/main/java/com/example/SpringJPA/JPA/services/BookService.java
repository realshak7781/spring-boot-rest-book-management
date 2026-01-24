package com.example.SpringJPA.JPA.services;

import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);
    List<BookEntity> findAll();

    Optional<BookEntity> findBookByIsbn(String isbn);

    public boolean isExists(String isbn);

    BookEntity updatePartial(String isbn, BookEntity bookDto);
}
