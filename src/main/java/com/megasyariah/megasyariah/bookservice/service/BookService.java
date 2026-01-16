package com.megasyariah.megasyariah.bookservice.service;

import com.megasyariah.megasyariah.bookservice.entity.Book;
import com.megasyariah.megasyariah.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book saveBook(Book book) {

        if (repository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException(
                "Book with ISBN " + book.getIsbn() + " already exists"
            );
        }

        return repository.save(book);
    }

    public Book updateBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
