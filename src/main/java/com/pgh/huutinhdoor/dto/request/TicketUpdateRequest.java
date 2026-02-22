package com.pgh.huutinhdoor.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pgh.huutinhdoor.enums.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TicketUpdateRequest {
    private String code;

    private String title;

    private String address;

    private LocalDateTime deadline;

    private BigDecimal totalAmount;

    private BigDecimal  deposit;

    private String paymentStatusNote;

}
