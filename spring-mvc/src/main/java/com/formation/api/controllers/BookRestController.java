package com.formation.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.api.dto.BookCreateDto;
import com.formation.api.dto.BookResponseDto;
import com.formation.api.mappers.BookMapper;
import com.formation.repo.BookRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<BookResponseDto> list() {
        return bookRepository.findAll().stream().map(BookMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookCreateDto dto) {
        var savedBook = bookRepository.save(BookMapper.fromCreate(dto));
        return ResponseEntity.created(URI.create("/api/books/" + savedBook.getId()))
                .body(BookMapper.toDto(savedBook));
    }

    // a ne pas faire
    @GetMapping("/test/{id}")
    public BookResponseDto getByIdTest(@PathVariable Long id) {
        return BookMapper.toDto(bookRepository.findById(id).orElse(null));

    }
}
