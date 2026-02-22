package com.pgh.huutinhdoor.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketItemUpdateRequest {
    // just update before: ticketStatus = "COMPLETED"
    private Long productId;
    private Long projectId;
    private BigDecimal priceAtTime;
    private Integer quantity;
}
