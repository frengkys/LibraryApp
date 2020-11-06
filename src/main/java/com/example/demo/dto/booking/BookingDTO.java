package com.example.demo.dto.booking;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class BookingDTO {
    private long id;
    private String memberName;
    private String bookingStatus;
    private String createdAt;
    private String updatedAt;
    private List<BookingDTO.BookingBookData> bookingBookData;

    @Data
    @Accessors(chain = true)
    public static class BookingBookData {
        private String bookTitle;
        private String bookStatus;
        private String bookingReturnDate;
    }
}
