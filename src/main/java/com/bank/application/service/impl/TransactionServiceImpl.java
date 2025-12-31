package com.bank.application.service.impl;

import com.bank.application.dto.request.DepositRequest;
import com.bank.application.dto.request.WithdrawalRequest;
import com.bank.application.dto.response.ClientTransactionsDTO;
import com.bank.application.dto.response.TransactionDTO;
import com.bank.application.entity.BankTransaction;
import com.bank.application.exception.InsufficientBalanceException;
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
     * Process withdrawal - EXACT same logic as legacy withdrawn() method
     * 
     * Legacy logic:
     * 1. Get last balance for client
     * 2. Calculate new balance (current - withdrawal)
     * 3. Check if current balance >= 1500 AND new balance >= 1500
     * 4. If validation fails, throw exception with EXACT same error messages
     * 5. If valid, save transaction and return success
     */
    @Override
    public TransactionDTO processWithdrawal(WithdrawalRequest request) throws Exception {

        log.debug("Processing withdrawal for client: {}, amount: {}",
                request.getClientId(), request.getAmount());

        // Get last balance - EXACT same query as legacy
        List<String> balanceList = transactionRepository
                .findLastBalanceByClientId(request.getClientId());

        if (balanceList == null || balanceList.isEmpty()) {
            throw new InsufficientBalanceException("No balance found for client");
        }

        String currentBalanceStr = balanceList.get(0);
        int currentBalance = Integer.parseInt(currentBalanceStr);
        int withdrawalAmount = Integer.parseInt(request.getAmount());
        int newBalance = currentBalance - withdrawalAmount;

        // EXACT same validation as legacy: if (sa < 1500 || xy < 1500)
        if (currentBalance < 1500 || newBalance < 1500) {
            // EXACT same error messages as legacy
            String errorMsg = "Insufficient Amount\n" +
                    "Client Amount Is: " + currentBalanceStr + "\n" +
                    "Minimum Amount Require 1500 Rs. ";
            throw new InsufficientBalanceException(errorMsg);
        }

        // Create new transaction entity
        BankTransaction transaction = new BankTransaction();
        transaction.setBankClientId(request.getClientId());
        transaction.setDetails(request.getDetails());
        transaction.setWithdrawn(request.getAmount());
        transaction.setBalance(String.valueOf(newBalance));
        transaction.setCreated(new Date());

        // Save transaction - EXACT same as legacy session.save()
        BankTransaction savedTransaction = transactionRepository.save(transaction);

        log.info("Withdrawal processed successfully. Client: {}, New Balance: {}",
                request.getClientId(), newBalance);

        // Convert to DTO and return
        return convertToDTO(savedTransaction);
    }

    /**
     * Get all transactions for a client - EXACT same logic as legacy viewcls()
     * method
     * 
     * Legacy logic:
     * 1. Query all transactions for client (list() method)
     * 2. Get current balance (vish() method - gets last balance)
     * 3. Return both to JSP
     */
    @Override
    public ClientTransactionsDTO getClientTransactions(String clientId) {

        log.debug("Fetching transactions for client: {}", clientId);

        // Get all transactions - EXACT same query as legacy list()
        // Legacy: "From Emp_AddTrans WHERE clid = :clid"
        List<BankTransaction> transactions = transactionRepository
                .findByBankClientId(clientId);

        // Convert to DTOs
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(this::convertToDTO)
                .toList();

        // Get current balance - EXACT same query as legacy vish()
        // Legacy: "SELECT depo.amount FROM Emp_AddTrans depo WHERE depo.clid = :clid
        // ORDER BY depo.id DESC LIMIT 1"
        String currentBalance = null;
        try {
            List<String> balanceList = transactionRepository
                    .findLastBalanceByClientId(clientId);

            if (balanceList != null && !balanceList.isEmpty()) {
                currentBalance = balanceList.get(0);
            }
        } catch (Exception e) {
            log.warn("Error retrieving current balance: {}", e.getMessage());
        }

        log.info("Retrieved {} transactions for client: {}, Current balance: {}",
                transactions.size(), clientId, currentBalance);

        // Build response - EXACT same data as legacy (detailList + namount)
        return ClientTransactionsDTO.builder()
                .clientId(clientId)
                .currentBalance(currentBalance)
                .transactions(transactionDTOs)
                .build();
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
