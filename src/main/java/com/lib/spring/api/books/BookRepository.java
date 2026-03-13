package com.lib.spring.api.books;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByAuthor(String author);

	List<Book> findByTitleContaining(String keyword);

	List<Book> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
