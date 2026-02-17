package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.TicketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketItemRepository extends JpaRepository<TicketItem, Long> {
}
