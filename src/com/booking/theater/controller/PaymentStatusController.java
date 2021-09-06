package com.booking.theater.controller;

import com.booking.theater.data.BookingDetailsRepository;
import com.booking.theater.data.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Service
@RequestMapping(path="/paymentStatus")
public class PaymentStatusController {
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @PostMapping
    public @ResponseBody String openSeatsForBooking (@RequestParam long transactionID, @RequestParam PaymentStatus paymentStatus){
        bookingDetailsRepository.updatePaymentStatus(transactionID, paymentStatus);
        return "OK";
    }
}
