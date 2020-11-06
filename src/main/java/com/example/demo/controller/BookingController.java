package com.example.demo.controller;

import com.example.demo.dto.booking.BookingDTO;
import com.example.demo.dto.booking.CreateOrUpdateBookingDTO;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public void createNewBooking(@RequestBody CreateOrUpdateBookingDTO createOrUpdateBookingDTO) throws ParseException {
        bookingService.createNewBooking(createOrUpdateBookingDTO);
    }

    //    update booking to make change, like return book
    @PutMapping("/booking/{bookingId}")
    public void updateBooking(
            @PathVariable final long bookingId,
            @RequestBody CreateOrUpdateBookingDTO createOrUpdateBookingDTO) throws ParseException {
        bookingService.updateBookingByBookingId(bookingId, createOrUpdateBookingDTO);
    }

    @GetMapping("/booking-list")
    public BookingDTO getBookingList {
        return bookingService.getBookingList();
    }
}
