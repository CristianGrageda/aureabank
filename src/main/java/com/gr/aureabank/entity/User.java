package com.gr.aureabank.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor vacío requerido por JPA
    public User() {}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passwordHash=" + passwordHash + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dni=" + dni + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
}
