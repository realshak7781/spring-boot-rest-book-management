package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Iterable<Author> ageLessThan(int age);


//    using the HQL query Lang
    @Query("Select a from Author a where a.age > ?1")
    Iterable<Author> getAuthorsWithAgeGreaterThan(int age);
}
