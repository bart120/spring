package com.formation.api.dto;

public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private Integer year;

    public BookResponseDto(Long id, String title, String author, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Long getId() {
        return id;
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
