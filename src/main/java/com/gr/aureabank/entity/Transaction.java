package com.gr.aureabank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gr.aureabank.enums.CurrencyEnum;
import com.gr.aureabank.enums.TransactionStatusEnum;

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
@Table(name = "transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "source_account_id", nullable = false)
    private Account sourceAccount;
	
	@ManyToOne
	@JoinColumn(name = "destination_account_id", nullable = false)
    private Account destinationAccount;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "currency", nullable = false)
	private CurrencyEnum currency;
	
	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private TransactionStatusEnum status;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", sourceAccount=" + sourceAccount + ", destinationAccount="
				+ destinationAccount + ", amount=" + amount + ", currency=" + currency + ", description=" + description
				+ ", status=" + status + ", createdAt=" + createdAt + "]";
	}
}
