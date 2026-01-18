package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity,String> {
}
