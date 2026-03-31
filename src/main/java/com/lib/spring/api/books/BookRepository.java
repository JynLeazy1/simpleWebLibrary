package com.lib.spring.api.books;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Page<Book> findByAuthorContaining(String author, Pageable pageable);

	Page<Book> findByTitleContaining(String keyword, Pageable pageable);

	Page<Book> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

}
