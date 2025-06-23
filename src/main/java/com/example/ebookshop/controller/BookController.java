package com.example.ebookshop.controller;

import com.example.ebookshop.model.Book;
import com.example.ebookshop.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // GET /books
    @GetMapping("")
    public List<Book> getAllBooks() {
        System.out.println(">>> Fetching all books");
        return bookRepository.findAll();
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        return bookRepository.findById(id);
    }

    // GET /books/sort/price
    @GetMapping("/sort/price")
    public List<Book> getBooksSortedByPrice() {
        return bookRepository.findAllByOrderByPriceDesc();
    }

     // GET /books/sort/price/asc
    @GetMapping("/sort/price/asc")
    public List<Book> getBooksSortedByPriceAsc() {
        return bookRepository.findAllByOrderByPriceAsc();
    }

    // GET /books/sort/title
    @GetMapping("/sort/title")
    public List<Book> getBooksSortedByTitle() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    // GET /books/sort/id/asc
    @GetMapping("/sort/id/asc")
    public List<Book> getBooksSortedByIdAsc() {
        return bookRepository.findAllByOrderByIdAsc();
    }
    // POST /books
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    // PUT /books/{title}?price=25
    @PutMapping("/{id}")
    public String updatePrice(@PathVariable int id, @RequestParam(defaultValue = "0.0") double price, @RequestParam(defaultValue = "0") int qty, @RequestParam(defaultValue = "") String imageUrl) {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isPresent()) {
            Book book = optBook.get();
            book.setPrice(price);
            book.setQty(qty);
            book.setImageUrl(imageUrl);
            bookRepository.save(book);
            return "Updated price of " + id + " to " + price;
        } else {
            return "Book not found";
        }
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "Deleted book with ID " + id;
    }
}