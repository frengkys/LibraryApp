package com.example.demo.dto.booking;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreateOrUpdateBookingDTO {
    private Long memberId;
    private String bookingStatus;
    private List<BookingBookData> bookingBookData;

    @Data
    @Accessors(chain = true)
    public static class BookingBookData {
        private Long bookId;
        private Long bookingId;
        private String bookStatus;
        private String bookingReturnDate;
    }
}
