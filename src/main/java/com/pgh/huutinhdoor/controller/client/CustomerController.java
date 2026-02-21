package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.response.client.CustomerResponse;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.mapper.CustomerMapper;
import com.pgh.huutinhdoor.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

//    @GetMapping("/me")
//    public ResponseEntity<CustomerResponse> getMyProfile(Authentication authentication) {
//
//        String phone = authentication.getName();
//        Customer customer = customerService.findByPhoneOrThrow(phone);
//
//        return ResponseEntity.ok(customerMapper.toClientResponse(customer));
//    }
}
