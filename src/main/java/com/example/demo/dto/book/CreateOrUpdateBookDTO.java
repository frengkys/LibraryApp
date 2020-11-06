package com.example.demo.dto.book;

import lombok.Data;

@Data
public class CreateOrUpdateBookDTO {
    private String bookTitle;
    private String author;
    private Integer price;
}