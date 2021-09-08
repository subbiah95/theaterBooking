package com.booking.theater.data;

import com.booking.theater.model.PaymentStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookingDetailsRepository extends CrudRepository<BookingDetails, Long>{
    @Transactional
    @Modifying
    @Query("UPDATE BookingDetails b SET status = :status WHERE transaction_id = :transactionId and status = 2")
    public void updatePaymentStatus(@Param("transactionId") long tId, @Param("status") PaymentStatus paymentStatus);

    @Query("SELECT b.status from BookingDetails b WHERE transaction_id = :transactionId")
    public PaymentStatus getPaymentStatus(@Param("transactionId") long tId);
}