package com.example.demo.dto.book;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookDTO {
    private long id;
    private String bookTitle;
    private String author;
    private Integer price;
    private String createdAt;
    private String updatedAt;
}
