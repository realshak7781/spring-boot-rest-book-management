package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,String> {
}
