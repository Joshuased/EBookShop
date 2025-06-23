package com.example.ebookshop.repository;

import com.example.ebookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.*;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
    Optional<Book> findById(int id);
    List<Book> findAllByOrderByPriceDesc();
    List<Book> findAllByOrderByPriceAsc();
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByIdAsc();
}
