package com.example.SpringJPA.JPA.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String isbn;
    private String title;
    private AuthorDto authorDto;
}
