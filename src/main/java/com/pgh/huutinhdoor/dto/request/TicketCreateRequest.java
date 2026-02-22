package com.pgh.huutinhdoor.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pgh.huutinhdoor.enums.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TicketCreateRequest {

    @NotBlank
    private String code;

    private String title;

    private String address;

    private LocalDateTime deadline;

    @NotNull
    private BigDecimal totalAmount;

    private BigDecimal  deposit;

    private String paymentStatusNote;


    @NotNull
    private TicketType type;

    private List<TicketItemCreateRequest> items;

    @NotNull
    private Long customerId;
}
