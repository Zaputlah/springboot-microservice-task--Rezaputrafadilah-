package com.megasyariah.megasyariah.bookservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class BookPatchRequest {

    private String title;
    private String author;
    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public LocalDate getPublishedDate() { return publishedDate; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
