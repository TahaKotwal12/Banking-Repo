package com.bank.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for bank_admin_login table
 * Stores admin login credentials
 */
@Entity
@Table(name = "bank_admin_login")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAdminLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_admin_user", length = 50)
    private String userName;

    @Column(name = "bank_admin_pass", length = 50)
    private String password;

    @Column(name = "bank_admin_id", length = 50)
    private String bank_id;
}
