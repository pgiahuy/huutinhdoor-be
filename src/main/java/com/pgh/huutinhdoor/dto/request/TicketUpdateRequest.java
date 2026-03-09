package com.pgh.huutinhdoor.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pgh.huutinhdoor.enums.PaymentStatus;
import com.pgh.huutinhdoor.enums.TicketStatus;
import com.pgh.huutinhdoor.enums.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateRequest {
    private String code;

    private String title;

    private String address;

    private LocalDateTime deadline;

    private TicketStatus workStatus;

    private BigDecimal totalAmount;

    private BigDecimal  deposit;

    private PaymentStatus paymentStatus;

    private String paymentStatusNote;

}
