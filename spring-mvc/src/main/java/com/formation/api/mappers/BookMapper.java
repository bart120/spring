package com.formation.api.mappers;

import com.formation.api.dto.AuthorResponseDto;
import com.formation.api.dto.BookCreateDto;
import com.formation.api.dto.BookResponseDto;
import com.formation.api.dto.BookUpdateDto;
import com.formation.models.Author;
import com.formation.models.Book;

public class BookMapper {

    public static BookResponseDto toDto(Book book) {
        var authorDto = new AuthorResponseDto(book.getAuthor().getId(), book.getAuthor().getName());
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                authorDto,
                book.getYear());
    }

    public static Book fromCreate(BookCreateDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(new Author(dto.getAuthorId()));
        book.setYear(dto.getYear());
        return book;
    }

    public static void applyUpdate(BookUpdateDto dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setAuthor(new Author(dto.getAuthorId()));
        book.setYear(dto.getYear());
    }
}
