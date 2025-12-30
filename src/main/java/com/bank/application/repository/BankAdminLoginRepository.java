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
    @Query("SELECT login FROM BankAdminLogin login WHERE login.userName = :userName AND login.password = :password AND login.bank_id = :bank_id")
    Optional<BankAdminLogin> findByUserNameAndPasswordAndBankId(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("bank_id") String bank_id);
}
