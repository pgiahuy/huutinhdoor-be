package com.pgh.huutinhdoor.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pgh.huutinhdoor.enums.PaymentStatus;
import com.pgh.huutinhdoor.enums.TicketStatus;
import com.pgh.huutinhdoor.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private Long id;
    private String code;
    private String title;
    private String address;

    private LocalDateTime deadline;

    private BigDecimal totalAmount;
    private BigDecimal  deposit;
    private PaymentStatus paymentStatus;
    private String paymentStatusNote;
    private TicketStatus workStatus;
    private TicketType type;

    private CustomerShortResponse customer;
    private List<Long> ticketItemIds;


}
