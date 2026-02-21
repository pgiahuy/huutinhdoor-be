package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.CustomerCreateRequest;
import com.pgh.huutinhdoor.dto.request.CustomerUpdateRequest;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.exception.DuplicateResourceException;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.CustomerMapper;
import com.pgh.huutinhdoor.repository.CustomerRepository;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper  customerMapper;



    @Transactional(readOnly = true)
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findByIdOrThrow(Long id){
        return customerRepository.findById(id).orElseThrow(()
            -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    @Transactional(readOnly = true)
    public Customer findByPhoneOrThrow(String phone) {
        return customerRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with phone " + phone)
                );
    }

    @Transactional
    public Customer create(CustomerCreateRequest request){
        if(request.getPhone() != null && !request.getPhone().isEmpty()
                && customerRepository.existsByPhone(request.getPhone())){
            throw new DuplicateResourceException("Phone already exists");
        }
        Customer customer = customerMapper.toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Transactional
    public Customer update(Long id, CustomerUpdateRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Customer not found with id " + id));

        if(request.getPhone() != null && !request.getPhone().isEmpty()
                && !request.getPhone().equals(customer.getPhone())
                && customerRepository.existsByPhone(request.getPhone())){
            throw new DuplicateResourceException("Phone already exists");
        }

        EntityUtil.copyNoNullProperties(request, customer);
        Customer saved = customerRepository.save(customer);
        return saved;
    }

    @Transactional
    public void deleteById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()
        -> new ResourceNotFoundException("Customer not found with id " + id));
        customerRepository.delete(customer);
    }

}
