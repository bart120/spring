package com.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}