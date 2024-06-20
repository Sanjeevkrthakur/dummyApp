package com.example.dummy.service;

import com.example.dummy.model.Customer;
import com.example.dummy.model.User;
import com.example.dummy.repository.CustomerRepository;
import com.example.dummy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(User userReq) {
        User user = new User();
        user.setFullName(userReq.getFullName());
        user.setUserType(userReq.getUserType());
        user.setStatus("ACTIVE");
        user.setEmailId(userReq.getEmailId());
        user.setPassword(passwordEncoder.encode(userReq.getPassword())); // Encode password
        user.setCreatedBy(userReq.getCreatedBy());
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    public ResponseEntity<?> getCustomersByUserId(int userId) {
        Optional<User> user=userRepository.findById(userId);//.orElseThrow().getCustomers();
        if (!ObjectUtils.isEmpty(user)) {
            return ResponseEntity.ok(user.get().getCustomers());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found. please enter a valid userId!!");
        }
    }

    public ResponseEntity<?> addCustomerToUser(int userId, Customer customerReq) {
        Optional<User> user = userRepository.findById(userId);//.orElseThrow(() -> new RuntimeException("User Not found"));
        if (!ObjectUtils.isEmpty(user)) {
            Customer customer = new Customer();
            customer.setEmail(customerReq.getEmail());
            customer.setCompany(customerReq.getCompany());
            customer.setFullName(customerReq.getFullName());
            customer.setPhoneNumber(customerReq.getPhoneNumber());
            customer.setCountry(customerReq.getCountry());
            customer.setUser(user.get());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found. please enter a valid userId!!");

        }
    }
}

