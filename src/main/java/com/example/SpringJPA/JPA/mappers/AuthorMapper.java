package com.example.SpringJPA.JPA.mappers;

import com.example.SpringJPA.JPA.domain.dto.AuthorDto;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

//    converting Entity to DTO
    AuthorDto mapTo(AuthorEntity authorEntity);
//    converting DTO to Entity
    AuthorEntity mapFrom(AuthorDto authorDto);
}
