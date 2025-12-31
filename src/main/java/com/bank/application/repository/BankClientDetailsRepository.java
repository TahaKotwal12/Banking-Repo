package com.bank.application.repository;

import com.bank.application.entity.BankClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for BankClientDetails entity
 * EXACT same operations as legacy Emp_AddClient_DaoImpl
 */
@Repository
public interface BankClientDetailsRepository extends JpaRepository<BankClientDetails, Long> {

    /**
     * Find client by bank client ID
     */
    Optional<BankClientDetails> findByBankClientId(String bankClientId);

    /**
     * Find all clients by bank client ID - for edit client list
     * Legacy: "From Emp_AddClient WHERE bank_id = :clid"
     */
    @Query("FROM BankClientDetails WHERE bankClientId = :clientId")
    List<BankClientDetails> findAllByBankClientId(@Param("clientId") String clientId);
}
