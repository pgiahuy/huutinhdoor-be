package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.CustomerCreateRequest;
import com.pgh.huutinhdoor.dto.response.client.CustomerResponse;


import com.pgh.huutinhdoor.entity.Customer;

public class CustomerMapper {
    public Customer toEntity(CustomerCreateRequest request) {
        return Customer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .note(request.getNote())
                .build();
    }
    public CustomerResponse toClientResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .userId(customer.getUser().getId())
                .build();
    }

    public com.pgh.huutinhdoor.dto.response.admin.CustomerResponse toAdminResponse(Customer customer) {
        return com.pgh.huutinhdoor.dto.response.admin.CustomerResponse
                .builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .note(customer.getNote())
                .userId(customer.getUser().getId())
                .build();
    }

}
