package com.lib.spring.api.books;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book savedBook = bookRepository.save(book);
		return ResponseEntity.status(201).body(savedBook);
	}
	
	@GetMapping
	public Page<Book> retrieveAllBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) BigDecimal minPrice,
			@RequestParam(required = false) BigDecimal maxPrice,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);

		if (title != null) {
			return bookRepository.findByTitleContaining(title, pageable);
		}
		if (author != null) {
			return bookRepository.findByAuthorContaining(author, pageable);
		}
		if (minPrice != null && maxPrice != null) {
			return bookRepository.findByPriceBetween(minPrice, maxPrice, pageable);
		}
		return bookRepository.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) {
		return bookRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
		 return bookRepository.findById(id)
			        .map(existing -> {
			            existing.setTitle(book.getTitle());
			            existing.setAuthor(book.getAuthor());
			            existing.setPrice(book.getPrice());
			            existing.setImageUrl(book.getImageUrl());
			            return ResponseEntity.ok(bookRepository.save(existing));
			        })
			        .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		if (!bookRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		bookRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
