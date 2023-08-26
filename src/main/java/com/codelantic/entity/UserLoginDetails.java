package com.codelantic.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class UserLoginDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private LocalDateTime loginTime;
}
