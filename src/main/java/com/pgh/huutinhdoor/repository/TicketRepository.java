package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket,Long> {
    List<Ticket> findAllByDeadlineBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT SUM(t.totalAmount) FROM Ticket t WHERE t.isPaid = true")
    Double getTotalRevenue();
}
