package com.bank.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity for bank_emp_loginman table
 * Stores employee login history
 */
@Entity
@Table(name = "bank_emp_loginman")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankEmpLoginman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_emp_id", length = 50)
    private String bank_id;

    @Column(name = "bank_emp_user", length = 50)
    private String userName;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
