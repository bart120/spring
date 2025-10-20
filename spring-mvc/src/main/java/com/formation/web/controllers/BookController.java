package com.formation.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.web.models.Book;
import com.formation.web.services.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String addBook() {
        return "books/addBook";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("listBooks", bookService.getAllBooks());
        return "books/books";
    }
}
