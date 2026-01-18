package com.example.SpringJPA.JPA;

import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;

public class TestDataUtil {
    private TestDataUtil(){}

    public static AuthorEntity createAuthorA(){
        return AuthorEntity.builder()
                .name("Ayn Rand")
                .age(55)
                .build();
    }

    public static AuthorEntity createAuthorB(){
        return AuthorEntity.builder()
                .name("Cristiano Ronaldo")
                .age(40)
                .build();
    }

    public static AuthorEntity createAuthorC(){
        return AuthorEntity.builder()
                .name("Alex Xu")
                .age(45)
                .build();
    }



    public static BookEntity createBookA(final AuthorEntity authorEntity){
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Life of Pablo")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createBookB(final AuthorEntity authorEntity){
        return BookEntity.builder()
                .isbn("978-1-2345-6745-1")
                .title("Alex Xu System design")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createBookC(final AuthorEntity authorEntity){
        return BookEntity.builder()
                .isbn("978-1-2345-5889-8")
                .title("The FountainHead")
                .authorEntity(authorEntity)
                .build();
    }
}
