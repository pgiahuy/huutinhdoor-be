package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.CustomerCreateRequest;
import com.pgh.huutinhdoor.dto.request.CustomerUpdateRequest;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.repository.CustomerRepository;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository  userRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findByIdOrThrow(Long id){
        return customerRepository.findById(id).orElseThrow(()
            -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public Customer create(CustomerCreateRequest request){
        return null;
    }
    public Customer update(Long id, CustomerUpdateRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Customer not found with id " + id));
        EntityUtil.copyNoNullProperties(request, customer);
        Customer saved = customerRepository.save(customer);
        return saved;
    }

    public void deleteById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()
        -> new ResourceNotFoundException("Customer not found with id " + id));
        customerRepository.delete(customer);
    }

}
