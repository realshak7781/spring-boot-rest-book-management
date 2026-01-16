package com.example.SpringJPA.JPA;

import com.example.SpringJPA.JPA.domain.Author;
import com.example.SpringJPA.JPA.domain.Book;
import org.junit.jupiter.api.Test;

public class TestDataUtil {
    private TestDataUtil(){}

    public static Author createAuthorA(){
        return Author.builder()
                .name("Ayn Rand")
                .age(55)
                .build();
    }

    public static Author createAuthorB(){
        return Author.builder()
                .name("Cristiano Ronaldo")
                .age(40)
                .build();
    }

    public static Author createAuthorC(){
        return Author.builder()
                .name("Alex Xu")
                .age(45)
                .build();
    }



    public static Book createBookA(final Author author){
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Life of Pablo")
                .author(author)
                .build();
    }

    public static Book createBookB(final Author author){
        return Book.builder()
                .isbn("978-1-2345-6745-1")
                .title("Alex Xu System design")
                .author(author)
                .build();
    }

    public static Book createBookC(final Author author){
        return Book.builder()
                .isbn("978-1-2345-5889-8")
                .title("The FountainHead")
                .author(author)
                .build();
    }
}
