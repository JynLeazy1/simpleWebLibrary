package com.lib.spring.api.books;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		return ResponseEntity.status(201).body(bookService.createBook(book));
	}

	@GetMapping
	public Page<Book> retrieveAllBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) BigDecimal minPrice,
			@RequestParam(required = false) BigDecimal maxPrice,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return bookService.getBooks(title, author, minPrice, maxPrice, page, size);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) {
		Book book = bookService.getBook(id);
		return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
		Book updated = bookService.updateBook(id, book);
		return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		return bookService.deleteBook(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}
