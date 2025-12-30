package com.bank.application.repository;

import com.bank.application.entity.BankAdminLoginman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository for BankAdminLoginman entity
 * Provides database access for admin login history
 */
@Repository
public interface BankAdminLoginmanRepository extends JpaRepository<BankAdminLoginman, Long> {

    /**
     * Get last login timestamps for an admin
     * Ordered by ID descending (most recent first)
     */
    @Query("SELECT depo.created FROM BankAdminLoginman depo WHERE depo.bank_id = :bank_id ORDER BY depo.id DESC")
    List<Date> findLastLoginsByBankId(@Param("bank_id") String bank_id);
}
