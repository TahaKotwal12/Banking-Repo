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
    @Query("SELECT c FROM BankClientLogin c WHERE c.userName = :userName AND c.password = :password AND c.bank_id = :bankId")
    Optional<BankClientLogin> findByUserNameAndPasswordAndBankId(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("bankId") String bankId);

    /**
     * Find client by bank ID and password - for password change verification
     */
    @Query("SELECT c FROM BankClientLogin c WHERE c.bank_id = :bankId AND c.password = :password")
    Optional<BankClientLogin> findByBank_idAndPassword(
            @Param("bankId") String bankId,
            @Param("password") String password);
}
