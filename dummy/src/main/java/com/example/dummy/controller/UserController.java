package com.example.dummy.controller;

import com.example.dummy.model.Customer;
import com.example.dummy.model.User;
import com.example.dummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Endpoint to get all customers of a specific user by user ID
    @GetMapping("/{userId}/customers")
    public ResponseEntity<?> getCustomersByUserId(@PathVariable int userId) {
        return userService.getCustomersByUserId(userId);
    }

    // Endpoint to add a customer to a specific user by user ID
    @PostMapping("/{userId}/customers")
    public ResponseEntity<Customer> addCustomerToUser(@PathVariable int userId, @RequestBody Customer customer) {
        return (ResponseEntity<Customer>) userService.addCustomerToUser(userId, customer);

    }
}

