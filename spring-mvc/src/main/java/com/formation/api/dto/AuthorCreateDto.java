package com.formation.api.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorCreateDto {
    @NotBlank(message = "Le nom de l'auteur est obligatoire")
    private String name;

    public AuthorCreateDto() {
    }

    public AuthorCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
