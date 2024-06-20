package com.example.dummy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String emailId;
    private String userType;
    private String password;
    private String status;
    private int createdBy;
    private Date createdAt;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Customer> customers;
}
