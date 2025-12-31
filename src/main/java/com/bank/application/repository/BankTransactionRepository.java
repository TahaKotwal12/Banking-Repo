package com.bank.application.repository;

import com.bank.application.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for BankTransaction entity
 * EXACT same queries as legacy Emp_AddTrans_DaoImpl
 */
@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

    /**
     * Get last balance for a client - EXACT same as legacy query
     * Legacy: "SELECT depo.amount FROM Emp_AddTrans depo WHERE depo.clid = :clid
     * ORDER BY depo.id DESC LIMIT 1"
     */
    @Query("SELECT t.balance FROM BankTransaction t WHERE t.bankClientId = :clientId ORDER BY t.id DESC")
    List<String> findLastBalanceByClientId(@Param("clientId") String clientId);

    /**
     * Get all transactions for a client - EXACT same as legacy list() query
     * Legacy: "From Emp_AddTrans WHERE clid = :clid"
     */
    @Query("FROM BankTransaction t WHERE t.bankClientId = :clientId ORDER BY t.created DESC")
    List<BankTransaction> findByBankClientId(@Param("clientId") String clientId);
}
