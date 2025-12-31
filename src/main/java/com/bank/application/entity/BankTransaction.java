package com.bank.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity for bank_trans table
 * Stores transaction records (deposits and withdrawals)
 * EXACT same structure as legacy Emp_AddTrans entity
 */
@Entity
@Table(name = "bank_trans")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_client_id", length = 255)
    private String bankClientId;

    @Column(name = "details", length = 255)
    private String details;

    @Column(name = "balance", length = 255)
    private String balance;

    @Column(name = "deposit", length = 255)
    private String deposit;

    @Column(name = "withdrawn", length = 255)
    private String withdrawn;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
