package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.entity.TicketItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketItemRepository extends JpaRepository<TicketItem, Long> {
    Optional<TicketItem> findByIdAndTicketId(Long itemId, Long ticketId);

    @EntityGraph(attributePaths = {"ticket","product","project"})
    Optional<TicketItem> findWithDetailsById(Long id);
}
