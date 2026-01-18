package com.example.SpringJPA.JPA.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
//    auto assign the id on creating of new author enititi
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private long id;
    private String name;
    private Integer age;
}
