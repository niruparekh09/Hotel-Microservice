package com.nrv.booking_service.service.implmentation;

import com.nrv.booking_service.client.RoomClient;
import com.nrv.booking_service.exception.InvalidArgumentException;
import com.nrv.booking_service.exception.ResourceNotFoundException;
import com.nrv.booking_service.exception.RoomAlreadyBookedException;
import com.nrv.booking_service.log.BookingLogMessage;
import com.nrv.booking_service.model.Booking;
import com.nrv.booking_service.repository.BookingRepository;
import com.nrv.booking_service.request.BookingInsertionRequest;
import com.nrv.booking_service.response.APIResponse;
import com.nrv.booking_service.response.BookingResponse;
import com.nrv.booking_service.response.RoomResponse;
import com.nrv.booking_service.response.UpdateRoomAvailability;
import com.nrv.booking_service.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.nrv.booking_service.service.implmentation.BookingServiceHelper.*;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    BookingRepository repository;

    @Autowired
    RoomClient roomClient;

    @Override
    public List<BookingResponse> fetchAllBookings() {
        List<Booking> bookings = repository.findAll();
        List<BookingResponse> bookingResponseList = bookingListMapper(bookings);
        logger.info(BookingLogMessage.BOOKING_LIST_GET.getMessage());
        return bookingResponseList;
    }

    @Override
    public BookingResponse fetchABooking(String bookingId) {
        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        BookingResponse bookingResponse = getBookingResponse(booking);
        logger.info(BookingLogMessage.BOOKING_GET.getMessage(), bookingResponse.getBookingId());
        return bookingResponse;
    }

    @Override
    public List<BookingResponse> fetchABookingByCustomer(String customerId) {
        List<Booking> bookingListByCustomerId = repository.findByCustomerId(customerId);
        List<BookingResponse> bookingResponseList = bookingListMapper(bookingListByCustomerId);
        if (bookingListByCustomerId.isEmpty()) {
            logger.info("No Booking for customer with id: {}", customerId);
        } else {
            logger.info(BookingLogMessage.BOOKING_GET_BY_CUSTOMER.getMessage(),
                    bookingResponseList.get(0).getCustomerId());
        }
        return bookingResponseList;
    }

    @Override
    public BookingResponse addABooking(BookingInsertionRequest newBooking) {
        // Check if room is booked or not
        checkBooking(newBooking.getRoomId(), newBooking);
        // Once we do a booking we will put Room in HOLD
        RoomResponse roomResponse = updateAvailabilityOfRoom(newBooking);
        int totalStay = (int) ChronoUnit.DAYS.between
                (newBooking.getCheckInDate(),
                        newBooking.getCheckOutDate());
        // If a person is checking in and out in single day then he still needs to pay one nights price
        totalStay = (totalStay == 0) ? 1 : totalStay;
        Booking booking = getBooking(newBooking, totalStay * roomResponse.getPricePerNight());
        repository.save(booking);
        BookingResponse bookingResponse = getBookingResponse(booking);
        logger.info(BookingLogMessage.BOOKING_ADD.getMessage(), bookingResponse.getBookingId());
        return bookingResponse;
    }

    // Checking Booking Details
    private void checkBooking(String roomId, BookingInsertionRequest newBooking) {

        // Check if date is not in past
        if (newBooking.getCheckInDate().isBefore(LocalDate.now())) {
            throw new InvalidArgumentException("Check-In date can not be In Past");
        }

        // Checking if date is invalid
        if (newBooking.getCheckInDate().isAfter(newBooking.getCheckOutDate())) {
            throw new InvalidArgumentException("Check-Out date can not be before Check-In date");
        }

        RoomResponse roomResponse = roomClient.getARoom(roomId);
        logger.info("Fetch room with id: {} from Room Client", roomResponse.getRoomId());
        // Checking If room is available or not
        if (roomResponse.getAvailability().equals("BOOKED")
                || roomResponse.getAvailability().equals("HOLD")) {
            throw new RoomAlreadyBookedException("Room with id: " + roomId + " is already booked");
        }

        // Checking if room is already booked for following date
        List<Booking> bookingList = repository.findByRoomId(roomId);
        // No booking available with this room id
        if (bookingList.isEmpty()) {
            return;
        }

        for (Booking existingBooking : bookingList) {
            if (existingBooking.getBookingId().equals(roomResponse.getRoomId())) {
                // Check for overlap
                if (newBooking.getCheckInDate().isBefore(existingBooking.getCheckOutDate()) &&
                        newBooking.getCheckOutDate().isAfter(existingBooking.getCheckInDate().plusDays(1))) {
                    throw new RoomAlreadyBookedException("Room with id: " + roomId + " is already booked");
                }
            }
        }
    }

    // Updating Availability of room
    private RoomResponse updateAvailabilityOfRoom(BookingInsertionRequest newBooking) {
        RoomResponse roomResponse = roomClient.updateAvailabilityOfRoom
                (newBooking.getRoomId(),
                        UpdateRoomAvailability.builder()
                                .availability("HOLD")
                                .build());
        // Once payment is over we will put Room in BOOKED
        if (true) { // Add payment client in here, Once payment is successful, this statement will be complete
            roomClient.updateAvailabilityOfRoom
                    (newBooking.getRoomId(),
                            UpdateRoomAvailability.builder()
                                    .availability("BOOKED")
                                    .build());

        }
        logger.info("Updated room with id: {} from Room Client. It's now {}"
                , roomResponse.getRoomId(), roomResponse.getAvailability());
        return roomResponse;
    }

    @Override
    public BookingResponse updateABooking(String bookingId, BookingInsertionRequest updateBooking) {
        deleteABooking(bookingId); // Deleting a booking
        BookingResponse bookingResponse = addABooking(updateBooking);// Saving the updated booking
        logger.info(BookingLogMessage.BOOKING_UPDATE.getMessage(), bookingResponse.getBookingId());
        return bookingResponse;
    }

    @Override
    public APIResponse deleteABooking(String bookingId) {
        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        repository.delete(booking);
        updateAvailabilityOnDelete(booking);
        logger.info(BookingLogMessage.ROOM_DELETE.getMessage(), booking.getBookingId());
        return new APIResponse("Booking deleted by id: " + booking.getBookingId());
    }

    private void updateAvailabilityOnDelete(Booking deletedBooking){
        RoomResponse roomResponse = roomClient.updateAvailabilityOfRoom
                (deletedBooking.getRoomId(),
                        UpdateRoomAvailability.builder()
                                .availability("AVAILABLE")
                                .build());
    }
}
