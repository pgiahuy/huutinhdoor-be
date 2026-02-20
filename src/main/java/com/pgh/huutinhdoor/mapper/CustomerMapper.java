package com.pgh.huutinhdoor.mapper;

import com.pgh.huutinhdoor.dto.request.CustomerCreateRequest;
import com.pgh.huutinhdoor.dto.response.client.CustomerResponse;


import com.pgh.huutinhdoor.entity.Customer;

public class CustomerMapper {
    public Customer toEntity(CustomerCreateRequest request) {
        return Customer.builder().build();
    }
    public CustomerResponse toClientResponse(Customer customer) {
        return CustomerResponse.builder().build();
    }

    public com.pgh.huutinhdoor.dto.response.admin.CustomerResponse toAdminResponse(Customer customer) {
        return com.pgh.huutinhdoor.dto.response.admin.CustomerResponse
                .builder()
                .build();
    }

}
