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
    @Query("SELECT login FROM BankEmpLogin login WHERE login.userName = :userName AND login.password = :password AND login.bank_id = :bank_id")
    Optional<BankEmpLogin> findByUserNameAndPasswordAndBankId(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("bank_id") String bank_id);
}
