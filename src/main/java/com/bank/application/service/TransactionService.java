package com.bank.application.service;

import com.bank.application.dto.request.DepositRequest;
import com.bank.application.dto.request.WithdrawalRequest;
import com.bank.application.dto.response.ClientTransactionsDTO;
import com.bank.application.dto.response.TransactionDTO;

/**
 * Transaction Service Interface
 * EXACT same operations as legacy Emp_AddTrans_Action
 */
public interface TransactionService {

    /**
     * Process deposit - exact same logic as legacy deposit() method
     * 
     * @param request Deposit request with clientId, details, amount
     * @return Transaction DTO with updated balance
     */
    TransactionDTO processDeposit(DepositRequest request);

    /**
     * Process withdrawal - exact same logic as legacy withdrawn() method
     * Validates minimum balance of 1500 Rs.
     * 
     * @param request Withdrawal request with clientId, details, amount
     * @return Transaction DTO with updated balance
     * @throws Exception if insufficient balance
     */
    TransactionDTO processWithdrawal(WithdrawalRequest request) throws Exception;

    /**
     * Get all transactions for a client - exact same logic as legacy viewcls()
     * method
     * 
     * @param clientId Client ID
     * @return Client transactions with current balance
     */
    ClientTransactionsDTO getClientTransactions(String clientId);
}
