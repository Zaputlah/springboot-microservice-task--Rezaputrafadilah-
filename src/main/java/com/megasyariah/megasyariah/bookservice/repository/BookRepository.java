package com.megasyariah.megasyariah.bookservice.repository;

import com.megasyariah.megasyariah.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);
}
