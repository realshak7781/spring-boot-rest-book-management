package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends CrudRepository<BookEntity,String>,
        PagingAndSortingRepository<BookEntity,String> {
}
