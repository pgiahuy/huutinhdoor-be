package com.pgh.huutinhdoor.dto.response.admin;

import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String note;
    private Long UserId;
}
