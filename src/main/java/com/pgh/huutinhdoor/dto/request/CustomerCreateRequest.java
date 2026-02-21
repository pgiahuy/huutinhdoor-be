package com.pgh.huutinhdoor.dto.request;

import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

    private String address;

    private String note;
}
