package com.example.SpringJPA.JPA.controllers;


import com.example.SpringJPA.JPA.domain.dto.BookDto;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import com.example.SpringJPA.JPA.mappers.BookMapper;
import com.example.SpringJPA.JPA.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private final BookMapper bookMapper;
    private final BookService bookService;

    public BookController(BookMapper bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable String isbn,@RequestBody BookDto bookDto) {
        BookEntity bookEntity=bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity=bookService.createBook(isbn,bookEntity);

        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity),HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookService.findAll();

        return books.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> findBookByISBN(@PathVariable String isbn) {
        Optional<BookEntity> bookObject=bookService.findBookByIsbn(isbn);
        if(bookObject.isPresent()) {
            return new ResponseEntity<>(bookMapper.mapTo(bookObject.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
