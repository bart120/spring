package com.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.web.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}