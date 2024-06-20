package com.example.dummy.controller;

import com.example.dummy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    ResponseEntity<?> customerList(@RequestParam("pageNo") int pageNo,
                                   @RequestParam("pageSize") int pageSize,
                                   @RequestParam(value = "fullName",required = false) String fullName) {
        return customerService.getAllCustomers(pageNo, pageSize,fullName);
    }

}
