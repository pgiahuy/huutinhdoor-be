package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.controller.client.TicketController;
import com.pgh.huutinhdoor.dto.request.TicketCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.TicketResponse;
import com.pgh.huutinhdoor.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponse toResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .code(ticket.getCode())
                .title(ticket.getTitle())
                .address(ticket.getAddress())
                .deadline(ticket.getDeadline())
                .totalAmount(ticket.getTotalAmount())
                .deposit(ticket.getDeposit())
                .paymentStatus(ticket.getPaymentStatus())
                .paymentStatusNote(ticket.getPaymentStatusNote())
                .workStatus(ticket.getWorkStatus())
                .type(ticket.getType())
                .build();
    }

    public Ticket toEntity(TicketCreateRequest request) {
        return Ticket.builder()
                .code(request.getCode())
                .title(request.getTitle())
                .address(request.getAddress())
                .deadline(request.getDeadline())
                .totalAmount(request.getTotalAmount())
                .deposit(request.getDeposit())
                .paymentStatusNote(request.getPaymentStatusNote())
                .type(request.getType())
                .build();
    }
}
