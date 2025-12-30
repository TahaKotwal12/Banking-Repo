package com.bank.application.repository;

import com.bank.application.entity.BankEmpLoginman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository for BankEmpLoginman entity
 * Provides database access for employee login history
 */
@Repository
public interface BankEmpLoginmanRepository extends JpaRepository<BankEmpLoginman, Long> {

    /**
     * Get last login timestamps for an employee
     * Ordered by ID descending (most recent first)
     */
    @Query("SELECT depo.created FROM BankEmpLoginman depo WHERE depo.bank_id = :bank_id ORDER BY depo.id DESC")
    List<Date> findLastLoginsByBankId(@Param("bank_id") String bank_id);
}
