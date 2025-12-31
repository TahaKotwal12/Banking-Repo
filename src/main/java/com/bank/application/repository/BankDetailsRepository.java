package com.bank.application.repository;

import com.bank.application.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for BankDetails entity
 * Provides database access for bank branch operations
 * EXACT same functionality as legacy Bank_Detail_DaoImpl
 */
@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {

    // JpaRepository provides findAll() method - exact same as legacy list() query
    // No custom methods needed for basic list operation
}
