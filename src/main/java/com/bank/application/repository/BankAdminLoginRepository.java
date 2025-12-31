package com.bank.application.repository;

import com.bank.application.entity.BankAdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for BankAdminLogin entity
 * Provides database access for admin login operations
 */
@Repository
public interface BankAdminLoginRepository extends JpaRepository<BankAdminLogin, Long> {

    /**
     * Find admin by username, password, and bank_id
     * Exact match to legacy SQL query
     */
    @Query("SELECT a FROM BankAdminLogin a WHERE a.userName = :userName AND a.password = :password AND a.bank_id = :bankId")
    Optional<BankAdminLogin> findByUserNameAndPasswordAndBankId(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("bankId") String bankId);

    /**
     * Find admin by bank ID and password - for password change verification
     */
    @Query("SELECT a FROM BankAdminLogin a WHERE a.bank_id = :bankId AND a.password = :password")
    Optional<BankAdminLogin> findByBank_idAndPassword(
            @Param("bankId") String bankId,
            @Param("password") String password);
}
