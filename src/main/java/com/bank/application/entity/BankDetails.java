package com.bank.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for bank_details table
 * Stores bank branch information
 * EXACT same structure as legacy Bank_Detail entity
 */
@Entity
@Table(name = "bank_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "bank_branch_name", length = 45)
    private String bankBranchName;

    @Column(name = "bank_branch_add", length = 45)
    private String bankBranchAddress;

    @Column(name = "bank_branch_city", length = 45)
    private String bankBranchCity;

    @Column(name = "bank_branch_phone", length = 45)
    private String bankBranchPhone;
}
