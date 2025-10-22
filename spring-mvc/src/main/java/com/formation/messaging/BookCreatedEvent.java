package com.formation.messaging;

public class BookCreatedEvent {
    private Long bookId;
    private String title;

    public BookCreatedEvent() {
    }

    public BookCreatedEvent(Long bookId, String title) {
        this.bookId = bookId;
        this.title = title;

    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
