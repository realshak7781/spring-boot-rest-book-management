package com.example.SpringJPA.JPA.mappers;

import com.example.SpringJPA.JPA.domain.dto.BookDto;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel ="Spring")
public interface BookMapper {
//    to and from methods
    BookEntity  mapFrom(BookDto bookDto);
    BookDto mapTo(BookEntity bookEntity);
}
