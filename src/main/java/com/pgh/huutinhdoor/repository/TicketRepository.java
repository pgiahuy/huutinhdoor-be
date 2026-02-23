package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.enums.PaymentStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByPaymentStatusNot(PaymentStatus status);

    @Query("SELECT SUM(t.totalAmount) FROM Ticket t WHERE t.paymentStatus = 'PAID'")
    Double getTotalRevenue();

    List<Ticket> findByCustomerNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);

    @EntityGraph(attributePaths = {"items","customer"})
    Optional<Ticket> findWithDetailsById(Long id);
}
