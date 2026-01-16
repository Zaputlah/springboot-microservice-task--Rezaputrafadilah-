package com.megasyariah.megasyariah.bookservice.controller;

import com.megasyariah.megasyariah.bookservice.dto.*;
import com.megasyariah.megasyariah.bookservice.entity.Book;
import com.megasyariah.megasyariah.bookservice.ResourceNotFoundException;
import com.megasyariah.megasyariah.bookservice.service.BookService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(
            @Valid @RequestBody BookRequest request) {

        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPublishedDate()
        );

        Book saved = service.saveBook(book);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Book created successfully",
                        saved),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Books retrieved successfully",
                        service.getAllBooks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(
            @PathVariable Long id) {

        Book book = service.getBookById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id " + id));

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Book retrieved successfully",
                        book
                )
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {

        Book book = service.getBookById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id " + id));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPublishedDate(request.getPublishedDate());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Book updated successfully",
                        service.updateBook(book)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> partialUpdateBook(
            @PathVariable Long id,
            @RequestBody BookPatchRequest request) {

        Book book = service.getBookById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id " + id));

        if (request.getTitle() != null)
            book.setTitle(request.getTitle());
        if (request.getAuthor() != null)
            book.setAuthor(request.getAuthor());
        if (request.getIsbn() != null)
            book.setIsbn(request.getIsbn());
        if (request.getPublishedDate() != null)
            book.setPublishedDate(request.getPublishedDate());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Book updated partially successfully",
                        service.updateBook(book)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(
            @PathVariable Long id) {

        Book book = service.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book not found with id " + id));

        service.deleteBook(book.getId());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Book deleted successfully",
                        null
                )
        );
    }

}
