package com.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findTop10ByOrderByYearDesc();
}