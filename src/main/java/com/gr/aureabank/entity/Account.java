package com.gr.aureabank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gr.aureabank.enums.AccountStatusEnum;
import com.gr.aureabank.enums.CurrencyEnum;

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
@Table(name = "accounts")
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "cbu", nullable = false, unique = true)
    private String cbu;

    @Column(name = "alias", unique = true)
    private String alias;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private CurrencyEnum currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatusEnum status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + user + ", cbu=" + cbu + ", alias=" + alias + ", balance=" + balance
				+ ", currency=" + currency + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
}
