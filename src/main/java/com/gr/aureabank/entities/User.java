package com.gr.aureabank.entities;

import java.time.LocalDateTime;

import com.gr.aureabank.enums.UserStatusEnum;

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
@Getter
@Setter
@Table(name = "users")
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
    private UserStatusEnum status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passwordHash=" + passwordHash + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dni=" + dni + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
}
