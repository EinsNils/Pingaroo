package de.pingaroo.backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}