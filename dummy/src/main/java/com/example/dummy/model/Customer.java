package com.example.dummy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String company;
    private String country;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;


}
