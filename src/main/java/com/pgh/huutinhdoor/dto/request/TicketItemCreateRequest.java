package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.entity.Product;
import com.pgh.huutinhdoor.entity.Ticket;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TicketItemCreateRequest {
    @NotNull
    private Long ticketId;


    private Long productId;
    private Long projectId;
    private Integer quantity;
}
