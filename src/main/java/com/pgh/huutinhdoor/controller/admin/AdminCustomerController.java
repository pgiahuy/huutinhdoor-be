package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.CustomerCreateRequest;
import com.pgh.huutinhdoor.dto.response.admin.CustomerResponse;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.mapper.CustomerMapper;
import com.pgh.huutinhdoor.repository.CustomerRepository;
import com.pgh.huutinhdoor.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/customers")
@RequiredArgsConstructor
public class AdminCustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll(){
        List<CustomerResponse> result = customerService.findAll().stream()
                .map(customerMapper::toAdminResponse).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id){
        Customer result = customerService.findByIdOrThrow(id);
        return ResponseEntity.ok(customerMapper.toAdminResponse(result));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerCreateRequest request){
        Customer customer = customerService.create(request);

        return ResponseEntity.created(URI.create("/api/v1/admin/customers/" + customer.getId()))
                .body(customerMapper.toAdminResponse(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable Long id){
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
