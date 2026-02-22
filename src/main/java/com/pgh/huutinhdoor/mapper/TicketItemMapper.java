package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.TicketItemCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TicketItemResponse;
import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.entity.TicketItem;
import org.springframework.stereotype.Component;

@Component
public class TicketItemMapper {
    public TicketItem toEntity(TicketItemCreateRequest request) {
        return TicketItem.builder()
                .priceAtTime(request.getPriceAtTime())
                .quantity(request.getQuantity())
                .build();
    }
    public TicketItemResponse toResponse(TicketItem ticketItem) {
        return TicketItemResponse.builder()
                .id(ticketItem.getId())
                .priceAtTime(ticketItem.getPriceAtTime())
                .quantity(ticketItem.getQuantity())
                .ticketId(ticketItem.getTicket().getId())
                .productId(
                        ticketItem.getProduct()!=null ? ticketItem.getProduct().getId() : null
                )
                .projectId(
                        ticketItem.getProject()!=null ? ticketItem.getProject().getId() : null
                )
                .build();
    }
}
