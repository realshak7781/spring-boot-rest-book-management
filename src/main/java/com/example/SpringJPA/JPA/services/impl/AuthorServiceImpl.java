package com.example.SpringJPA.JPA.services.impl;

import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.repositories.AuthorRepository;
import com.example.SpringJPA.JPA.repositories.BookRepository;
import com.example.SpringJPA.JPA.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;;
    }
    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {

//        NEEDS PAGINATION TO AVOID CRASHING
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorEntity findAuthorById(Long id) {
       Optional<AuthorEntity> authorEntity=authorRepository.findById(id);

       return authorEntity.orElse(null);
    }

    @Override
    public boolean isExists(Long id) {
       return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity updatePartial(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor ->{
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not Exist"));
    }

    @Override
    public AuthorEntity deleteAuthor(Long id) {
        Optional<AuthorEntity> deletedAuthorEntity=authorRepository.findById(id);
        authorRepository.deleteById(id);

        return deletedAuthorEntity.orElse(null);
    }
}
