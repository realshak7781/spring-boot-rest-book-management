package com.example.SpringJPA.JPA.mappers;

import com.example.SpringJPA.JPA.domain.dto.BookDto;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="Spring")
public interface BookMapper {
//    to and from methods

    @Mapping(source="authorDto",target = "authorEntity")
    BookEntity  mapFrom(BookDto bookDto);
    @Mapping(source="authorEntity",target = "authorDto")
    BookDto mapTo(BookEntity bookEntity);
}
