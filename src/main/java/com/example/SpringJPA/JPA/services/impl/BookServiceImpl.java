package com.example.SpringJPA.JPA.services.impl;

import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import com.example.SpringJPA.JPA.repositories.BookRepository;
import com.example.SpringJPA.JPA.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }
}
