package com.bank.application.service.impl;

import com.bank.application.dto.request.DepositRequest;
import com.bank.application.dto.response.TransactionDTO;
import com.bank.application.entity.BankTransaction;
import com.bank.application.repository.BankTransactionRepository;
import com.bank.application.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Transaction Service Implementation
 * EXACT same logic as legacy Emp_AddTrans_DaoImpl
 */
@Slf4j
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private BankTransactionRepository transactionRepository;

    /**
     * Process deposit - EXACT same logic as legacy deposit() method
     * 
     * Legacy logic:
     * 1. Get last balance for client
     * 2. If no previous balance, use deposit amount as balance
     * 3. If previous balance exists, add deposit to it
     * 4. Save new transaction with updated balance
     * 5. Return success
     */
    @Override
    public TransactionDTO processDeposit(DepositRequest request) {

        log.debug("Processing deposit for client: {}, amount: {}",
                request.getClientId(), request.getAmount());

        // Create new transaction entity
        BankTransaction transaction = new BankTransaction();
        transaction.setBankClientId(request.getClientId());
        transaction.setDetails(request.getDetails());
        transaction.setDeposit(request.getAmount());
        transaction.setCreated(new Date());

        // Get last balance - EXACT same query as legacy
        String newBalance;
        try {
            List<String> balanceList = transactionRepository
                    .findLastBalanceByClientId(request.getClientId());

            if (balanceList != null && !balanceList.isEmpty()) {
                String lastBalance = balanceList.get(0);

                if (lastBalance == null) {
                    // No previous balance - use deposit amount
                    newBalance = request.getAmount();
                } else {
                    // Add deposit to previous balance - EXACT same logic
                    int previousBalance = Integer.parseInt(lastBalance);
                    int depositAmount = Integer.parseInt(request.getAmount());
                    int updatedBalance = previousBalance + depositAmount;
                    newBalance = String.valueOf(updatedBalance);
                }
            } else {
                // First transaction for this client
                newBalance = request.getAmount();
            }
        } catch (Exception e) {
            // If any error, use deposit amount as balance - EXACT same as legacy
            log.warn("Error retrieving last balance, using deposit amount: {}", e.getMessage());
            newBalance = request.getAmount();
        }

        // Set the new balance
        transaction.setBalance(newBalance);

        // Save transaction - EXACT same as legacy session.save()
        BankTransaction savedTransaction = transactionRepository.save(transaction);

        log.info("Deposit processed successfully. Client: {}, New Balance: {}",
                request.getClientId(), newBalance);

        // Convert to DTO and return
        return convertToDTO(savedTransaction);
    }

    /**
     * Convert entity to DTO
     */
    private TransactionDTO convertToDTO(BankTransaction entity) {
        return TransactionDTO.builder()
                .id(entity.getId())
                .clientId(entity.getBankClientId())
                .details(entity.getDetails())
                .balance(entity.getBalance())
                .deposit(entity.getDeposit())
                .withdrawn(entity.getWithdrawn())
                .created(entity.getCreated())
                .build();
    }
}
