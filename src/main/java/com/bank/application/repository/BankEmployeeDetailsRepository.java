package com.bank.application.repository;

import com.bank.application.entity.BankEmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for BankEmployeeDetails entity
 * EXACT same operations as legacy Admin_AddEmp_DaoImpl
 */
@Repository
public interface BankEmployeeDetailsRepository extends JpaRepository<BankEmployeeDetails, Long> {

    /**
     * Find employee by bank employee ID
     */
    Optional<BankEmployeeDetails> findByBankEmpId(String bankEmpId);
}
