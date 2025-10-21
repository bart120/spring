package com.formation.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.api.dto.AuthorCreateDto;
import com.formation.api.dto.AuthorResponseDto;
import com.formation.api.dto.BookCreateDto;
import com.formation.api.dto.BookResponseDto;
import com.formation.api.mappers.AuthorMapper;
import com.formation.api.mappers.BookMapper;
import com.formation.repo.AuthorRepository;
import com.formation.repo.BookRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorRepository authorRepository;

    public AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<AuthorResponseDto> list(Pageable pageable) {

        return authorRepository.findAll(pageable).stream().map(AuthorMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(AuthorMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@Valid @RequestBody AuthorCreateDto dto) {
        var savedAuthor = authorRepository.save(AuthorMapper.fromCreate(dto));
        return ResponseEntity.created(URI.create("/api/authors/" + savedAuthor.getId()))
                .body(AuthorMapper.toDto(savedAuthor));
    }

}
