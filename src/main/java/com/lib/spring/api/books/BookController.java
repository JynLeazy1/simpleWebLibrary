package com.lib.spring.api.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@PostMapping("/api/book")
	public Book createBook(@RequestBody Book book) {
		Book savedBook = bookRepository.save(book);
		return savedBook;
		
	}
	
	@GetMapping("/api/books")
	public List<Book> retreiveAllBooks(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/api/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) {
		return bookRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/api/book/{id}")
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
	
	@DeleteMapping("/api/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		bookRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
