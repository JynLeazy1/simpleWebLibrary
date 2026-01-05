package com.lib.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lib.spring.books.BookRepository;

@Controller
public class BookViewController {

    private final BookRepository bookRepository;

    public BookViewController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books"; // templates/books.html
    }

    @GetMapping("/books/{id}")
    public String bookDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElseThrow());
        return "book-detail"; // templates/book-detail.html
    }
}
