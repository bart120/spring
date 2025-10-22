package com.formation.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.formation.api.dto.BookCreateDto;
import com.formation.api.dto.BookResponseDto;
import com.formation.api.dto.BookUpdateDto;
import com.formation.api.mappers.BookMapper;
import com.formation.messaging.BookCreatedEvent;
import com.formation.publishers.BookEventPublisher;
import com.formation.repo.BookRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;
    private final BookEventPublisher bookEventPublisher;

    public BookRestController(BookRepository bookRepository, BookEventPublisher bookEventPublisher) {
        this.bookRepository = bookRepository;
        this.bookEventPublisher = bookEventPublisher;
    }

    /*
     * @GetMapping
     * public List<BookResponseDto> list(
     * 
     * @RequestParam(defaultValue = "0") int page,
     * 
     * @RequestParam(defaultValue = "10") int size,
     * 
     * @RequestParam(defaultValue = "id") String sortBy,
     * 
     * @RequestParam(defaultValue = "asc") String sortDirection) {
     * 
     * var pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
     * 
     * return
     * bookRepository.findAll(pageRequest).stream().map(BookMapper::toDto).toList();
     * }
     */

    /*
     * @GetMapping
     * public List<BookResponseDto> list(
     * 
     * @RequestParam(defaultValue = "0") int page,
     * 
     * @RequestParam(defaultValue = "10") int size,
     * 
     * @RequestParam(defaultValue = "id") String sort,
     * 
     * @RequestParam(defaultValue = "asc") String dir) {
     * 
     * var pageRequest = PageRequest.of(page, size, Sort.by(dir, sort));
     * 
     * return
     * bookRepository.findAll(pageRequest).stream().map(BookMapper::toDto).toList();
     * }
     */

    @GetMapping
    public List<BookResponseDto> list(Pageable pageable) {

        return bookRepository.findAll(pageable).stream().map(BookMapper::toDto).toList();
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

        bookEventPublisher.publishCreated(new BookCreatedEvent(savedBook.getId(), savedBook.getTitle()));

        return ResponseEntity.created(URI.create("/api/books/" + savedBook.getId()))
                .body(BookMapper.toDto(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable Long id,
            @Valid @RequestBody BookUpdateDto dto) {

        return bookRepository.findById(id).map(b -> {
            BookMapper.applyUpdate(dto, b);
            var savedBook = bookRepository.save(b);
            return ResponseEntity.ok(BookMapper.toDto(savedBook));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (bookRepository.existsById(id))
            bookRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
    }

    // a ne pas faire
    @GetMapping("/test/{id}")
    public BookResponseDto getByIdTest(@PathVariable Long id) {
        return BookMapper.toDto(bookRepository.findById(id).orElse(null));

    }
}
