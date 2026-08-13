package com.gr.aureabank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gr.aureabank.enums.MovementTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account_movements")
public class AccountMovement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "transaction_id", nullable = false)
	private Transaction transaction;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private MovementTypeEnum type;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "balance_after", nullable = false)
	private BigDecimal balanceAfter;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Override
	public String toString() {
		return "AccountMovement [id=" + id + ", account=" + account + ", transaction=" + transaction + ", type=" + type
				+ ", amount=" + amount + ", balanceAfter=" + balanceAfter + ", createdAt=" + createdAt + "]";
	}
}
