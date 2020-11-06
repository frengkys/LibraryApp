package com.example.demo.service;

import com.example.demo.dto.booking.BookingDTO;
import com.example.demo.dto.booking.CreateOrUpdateBookingDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingDetail;
import com.example.demo.entity.Member;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookingDetailRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void createNewBooking(CreateOrUpdateBookingDTO createOrUpdateBookingDTO) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<CreateOrUpdateBookingDTO.BookingBookData> bookingBookData = createOrUpdateBookingDTO.getBookingBookData();

         Member member = memberRepository.findById(createOrUpdateBookingDTO.getMemberId()).orElseThrow(() -> new ResourceNotFound(String.format("Member not found", createOrUpdateBookingDTO.getMemberId())));
        // End of validating

        // Start process save to DB
        Booking booking = new Booking()
                .setMemberID(member.getId())
                .setBooking_status(createOrUpdateBookingDTO.getBookingStatus());

        bookingRepository.save(booking);

        for (int i = 0; i < bookingBookData.size(); i++) {
            System.out.println("Test ->" + bookingBookData.get(i));

            // Start validate bookId
            Book bookResponse = bookRepository.findById(bookingBookData.get(i).getBookId()).orElseThrow(() -> new ResourceNotFound(String.format("Book not found")));
            // End of validate bookId

            BookingDetail bookingDetail = new BookingDetail()
                    .setBook_id(bookResponse.getId())
                    .setBookingId(booking.getId())
                    .setBook_status(bookingBookData.get(i).getBookStatus())
                    .setBooking_return_date(simpleDateFormat.parse(bookingBookData.get(i).getBookingReturnDate()));

            bookingDetailRepository.save(bookingDetail);
        }
        // End process save to DB
    }

    @Transactional
    public void updateBookingByBookingId(long bookingId, CreateOrUpdateBookingDTO createOrUpdateBookingDTO) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound(String.format("Booking not found", bookingId)));

        List<CreateOrUpdateBookingDTO.BookingBookData> bookingBookData = createOrUpdateBookingDTO.getBookingBookData();

        List<BookingDetail> bookingDetail = bookingDetailRepository.findByBookingId(bookingId);

        booking
                .setMemberID(createOrUpdateBookingDTO.getMemberId())
                .setBooking_status(createOrUpdateBookingDTO.getBookingStatus());

        bookingRepository.save(booking);

        for (int i = 0; i < bookingBookData.size(); i++) {

            bookingDetail.get(i)
                    .setBookingId(booking.getId())
                    .setBook_id(bookingBookData.get(i).getBookId())
                    .setBook_status(bookingBookData.get(i).getBookStatus())
                    .setBooking_return_date(simpleDateFormat.parse(bookingBookData.get(i).getBookingReturnDate()));

            bookingDetailRepository.saveAll(bookingDetail);
        }
    }

    public BookingDTO getBookingList() {
        // cari semua bookingan
        // klo udh dapetin bookingannya, cari semua memberId yg booking (distinct)
        // cari semua member berdasrkan memberId tsb --> select * from users where id in (:memberId)
        // ketika looping booking utk json response

        List<BookingDetail> bookingDetails = bookingDetailRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> bookingByMembers = bookingRepository.findDistinctByMemberId(1);
        List<Member> members = memberRepository.findByMemberId(1); // temp memberId

        return bookings.stream().map(this::convertBookingToBookingDTO).collect(Collectors.toList());
        // BookingDTO bdto;
//        return convertBookingToBookingDTO(bookings, bookingDetails, members);
   }

    private BookingDTO convertBookingToBookingDTO(Member members) {
        return new BookingDTO()
                .setMemberName(members.getName());
//                .setBookingStatus(booking.getBooking_status());
    }
}
