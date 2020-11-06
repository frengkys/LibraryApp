package com.example.demo.service;

import com.example.demo.dto.book.BookDTO;
import com.example.demo.dto.book.CreateOrUpdateBookDTO;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(this::convertMemberToBookDTO).collect(Collectors.toList());
    }

    public BookDTO findById(final long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(String.format("Book not found %d ", id)));

        return convertMemberToBookDTO(book);
    }

    public BookDTO createNewBook(CreateOrUpdateBookDTO createOrUpdateBookDTO) {
        Book book = new Book()
                .setBook_title(createOrUpdateBookDTO.getBookTitle())
                .setPrice(createOrUpdateBookDTO.getPrice())
                .setAuthor(createOrUpdateBookDTO.getAuthor());

        bookRepository.save(book);

        return convertMemberToBookDTO(book);
    }

    public void updateBookById(long id, CreateOrUpdateBookDTO createOrUpdateBookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound((String.format("Book not found", id))));

        book
                .setAuthor(createOrUpdateBookDTO.getAuthor())
                .setPrice(createOrUpdateBookDTO.getPrice())
                .setBook_title(createOrUpdateBookDTO.getBookTitle());

        bookRepository.save(book);
    }

    private BookDTO convertMemberToBookDTO(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setCreatedAt(book.getCreatedAt().toString())
                .setUpdatedAt(book.getUpdatedAt().toString())
                .setBookTitle(book.getBook_title())
                .setPrice(book.getPrice())
                .setAuthor(book.getAuthor());
    }
}
