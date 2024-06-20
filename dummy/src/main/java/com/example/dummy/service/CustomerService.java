package com.example.dummy.service;

import com.example.dummy.model.Customer;
import com.example.dummy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> getAllCustomers(int pageNo, int pageSize, String fullName) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        Page<Customer> customers;
        if (!StringUtils.isEmpty(fullName)) {
            customers = customerRepository.findAllByFullName(fullName, pageable);
        } else {
            customers = customerRepository.findAll(pageable);
            if (customers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customers not found");
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
