package com.bank.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity for bank_admin_loginman table
 * Stores admin login history
 */
@Entity
@Table(name = "bank_admin_loginman")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAdminLoginman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_admin_id", length = 50)
    private String bank_id;

    @Column(name = "bank_admin_user", length = 50)
    private String userName;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
