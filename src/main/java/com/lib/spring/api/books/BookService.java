package com.lib.spring.api.books;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public Page<Book> getBooks(String title, String author, BigDecimal minPrice, BigDecimal maxPrice, int page, int size) {
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

	public Book getBook(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	public Book updateBook(int id, Book book) {
		return bookRepository.findById(id)
				.map(existing -> {
					existing.setTitle(book.getTitle());
					existing.setAuthor(book.getAuthor());
					existing.setPrice(book.getPrice());
					existing.setImageUrl(book.getImageUrl());
					return bookRepository.save(existing);
				})
				.orElse(null);
	}

	public boolean deleteBook(int id) {
		if (!bookRepository.existsById(id)) {
			return false;
		}
		bookRepository.deleteById(id);
		return true;
	}
}
