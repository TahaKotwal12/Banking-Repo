package com.bank.application.repository;

import com.bank.application.entity.BankEmpLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for BankEmpLogin entity
 * Provides database access for employee login operations
 */
@Repository
public interface BankEmpLoginRepository extends JpaRepository<BankEmpLogin, Long> {

    /**
     * Find employee by username, password, and bank_id
     * Exact match to legacy SQL query
     */
    @Query("SELECT e FROM BankEmpLogin e WHERE e.userName = :userName AND e.password = :password AND e.bank_id = :bankId")
    Optional<BankEmpLogin> findByUserNameAndPasswordAndBankId(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("bankId") String bankId);

    /**
     * Find employee by bank ID and password - for password change verification
     */
    @Query("SELECT e FROM BankEmpLogin e WHERE e.bank_id = :bankId AND e.password = :password")
    Optional<BankEmpLogin> findByBank_idAndPassword(
            @Param("bankId") String bankId,
            @Param("password") String password);
}
