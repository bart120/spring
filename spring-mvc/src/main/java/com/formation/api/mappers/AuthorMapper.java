package com.formation.api.mappers;

import com.formation.api.dto.AuthorCreateDto;
import com.formation.api.dto.AuthorResponseDto;
import com.formation.models.Author;

public class AuthorMapper {

    public static AuthorResponseDto toDto(Author author) {
        return new AuthorResponseDto(author.getId(), author.getName());
    }

    public static Author fromCreate(AuthorCreateDto dto) {
        var author = new Author();
        author.setName(dto.getName());
        return author;
    }
}
