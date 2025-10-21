package com.formation.api.mappers;

import com.formation.api.dto.BookCreateDto;
import com.formation.api.dto.BookResponseDto;
import com.formation.api.dto.BookUpdateDto;
import com.formation.models.Book;

public class BookMapper {

    public static BookResponseDto toDto(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public static Book fromCreate(BookCreateDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
        return book;
    }

    public static void applyUpdate(BookUpdateDto dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
    }
}
