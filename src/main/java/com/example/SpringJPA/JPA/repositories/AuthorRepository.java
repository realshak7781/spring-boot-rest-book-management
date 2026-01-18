package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

}
