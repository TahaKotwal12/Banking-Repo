package com.bank.application.repository;

import com.bank.application.entity.BankClientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for BankClientLogin entity
 * Provides database access for client login operations
 */
@Repository
public interface BankClientLoginRepository extends JpaRepository<BankClientLogin, Long> {

    /**
     * Find client by username, password, and bank_id
     * Exact match to legacy SQL query
     */
    @Query("SELECT login FROM BankClientLogin login WHERE login.userName = :userName AND login.password = :password AND login.bank_id = :bank_id")
    Optional<BankClientLogin> findByUserNameAndPasswordAndBankId(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("bank_id") String bank_id);
}
