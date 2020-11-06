package com.example.demo.controller;

import com.example.demo.dto.book.BookDTO;
import com.example.demo.dto.book.CreateOrUpdateBookDTO;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<BookDTO> getBooksList() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public BookDTO getBookById(@PathVariable final Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/book")
    public BookDTO createNewBook(@RequestBody CreateOrUpdateBookDTO createOrUpdateBookDTO) {
        return bookService.createNewBook(createOrUpdateBookDTO);
    }

    @PutMapping("/book/{id}")
    public void updateBookById(@PathVariable final long id, @RequestBody CreateOrUpdateBookDTO createOrUpdateBookDTO) {
        bookService.updateBookById(id, createOrUpdateBookDTO);
    }
}
