package com.example.SpringJPA.JPA;

import com.example.SpringJPA.JPA.domain.dto.AuthorDto;
import com.example.SpringJPA.JPA.domain.dto.BookDto;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;
public class TestDataUtil {
    private TestDataUtil(){}

    // ---------------- AUTHORS (ENTITIES) ----------------

    public static AuthorEntity createAuthorA(){
        return AuthorEntity.builder()
                .name("Ayn Rand")
                .age(55)
                .build();
    }

    public static AuthorEntity createAuthorB(){
        return AuthorEntity.builder()
                .name("Frank Herbert")
                .age(40)
                .build();
    }

    public static AuthorEntity createAuthorC(){
        return AuthorEntity.builder()
                .name("Alex Xu")
                .age(45)
                .build();
    }

    public static AuthorEntity createAuthorD() {
        return AuthorEntity.builder()
                .name("Sharique Akhtar")
                .age(22)
                .build();
    }

    // NEW: Author E
    public static AuthorEntity createAuthorE() {
        return AuthorEntity.builder()
                .name("Martin Fowler")
                .age(58)
                .build();
    }

    // NEW: Author F
    public static AuthorEntity createAuthorF() {
        return AuthorEntity.builder()
                .name("Robert C. Martin")
                .age(70)
                .build();
    }

    // ---------------- AUTHORS (DTOs) ----------------
    // Added corresponding DTOs for all entities for Controller testing

    public static AuthorDto createAuthorDtoA() {
        return AuthorDto.builder()
                .id(1L) // DTOs usually carry IDs in updates
                .name("Ayn Rand")
                .age(55)
                .build();
    }

    public static AuthorDto createAuthorDtoB() {
        return AuthorDto.builder()
                .id(2L)
                .name("Frank Herbert")
                .age(40)
                .build();
    }

    public static AuthorDto createAuthorDtoC() {
        return AuthorDto.builder()
                .id(3L)
                .name("Alex Xu")
                .age(45)
                .build();
    }

    public static AuthorDto createAuthorDtoD() {
        return AuthorDto.builder()
                .id(4L)
                .name("Sharique Akhtar")
                .age(22)
                .build();
    }

    // ---------------- BOOKS (ENTITIES) ----------------

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

    // NEW: Book D
    public static BookEntity createBookD(final AuthorEntity authorEntity){
        return BookEntity.builder()
                .isbn("978-0-13-475759-9")
                .title("Refactoring")
                .authorEntity(authorEntity)
                .build();
    }

    // NEW: Book E
    public static BookEntity createBookE(final AuthorEntity authorEntity){
        return BookEntity.builder()
                .isbn("978-0-321-12521-7")
                .title("Domain-Driven Design")
                .authorEntity(authorEntity)
                .build();
    }

    // ---------------- BOOKS (DTOs) ----------------
    // Added matching DTOs for the books

    public static BookDto createBookDtoA(final AuthorDto authorDto){
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Life of Pablo")
                .authorDto(authorDto)
                .build();
    }

    public static BookDto createBookDtoB(final AuthorDto authorDto){
        return BookDto.builder()
                .isbn("978-1-2345-6745-1")
                .title("Alex Xu System design")
                .authorDto(authorDto)
                .build();
    }

    public static BookDto createBookDtoC(final AuthorDto authorDto){
        return BookDto.builder()
                .isbn("978-1-2345-5889-8")
                .title("The FountainHead")
                .authorDto(authorDto)
                .build();
    }

    public static BookDto createBookDtoD(final AuthorDto authorDto){
        return BookDto.builder()
                .isbn("978-0-13-475759-9")
                .title("Refactoring")
                .authorDto(authorDto)
                .build();
    }
}