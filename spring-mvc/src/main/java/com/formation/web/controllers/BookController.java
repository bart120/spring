package com.formation.web.controllers;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.formation.models.Book;
import com.formation.repo.BookRepository;
import com.formation.web.services.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    // private final BookService bookService;

    public BookController(BookRepository bookRepository/* , BookService bookService */) {
        this.bookRepository = bookRepository;
        // this.bookService = bookService;
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        // Book book = bookRepository.findById(id).orElse(null);

        httpSession.setAttribute("user_name", "Bob");

        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre introuvable"));
        model.addAttribute("book", book);
        return "books/detailBook";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {

        // bookService.addBook(book);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String addBook(HttpSession httpSession) {
        var name = httpSession.getAttribute("user_name");
        return "books/addBook";
    }

    @GetMapping("/books")
    // @PreAuthorize("hasRole('USER')")
    public String listBooks(Model model) {
        model.addAttribute("listBooks", bookRepository.findTop10ByOrderByYearDesc());
        return "books/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
