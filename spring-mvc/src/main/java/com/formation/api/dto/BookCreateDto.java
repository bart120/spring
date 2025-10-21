package com.formation.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookCreateDto {
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 1, max = 200, message = "Le titre doit faire entre 1 et 200 caractères")
    private String title;

    @NotBlank(message = "L'auteur est obligatoire")
    private String author;

    @Positive(message = "L'année doit être un entier positif")
    @Min(value = 1800, message = "L'année doit être supérieure à 1800")
    private Integer year;

    public BookCreateDto() {
    }

    public BookCreateDto(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

}
