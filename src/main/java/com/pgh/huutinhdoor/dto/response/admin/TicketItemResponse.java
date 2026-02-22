package com.pgh.huutinhdoor.dto.response.admin;

import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.entity.Ticket;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketItemResponse {
    private Long id;

    private Long ticketId;

    private Long productId;
    private Long projectId;

    private BigDecimal priceAtTime;
    private Integer quantity;
}
