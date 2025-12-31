package com.bank.application.service;

import com.bank.application.dto.request.DepositRequest;
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
}
